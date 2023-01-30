package by.lawaksoft.tradebot.service.api.trade.impl;

import by.lawaksoft.tradebot.client.TradeClient;
import by.lawaksoft.tradebot.config.security.OkxConfigSecurity;
import by.lawaksoft.tradebot.config.security.SecurityService;
import by.lawaksoft.tradebot.dto.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderDetailsDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderResponseDTO;
import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.dto.order.OrderResponseDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.entity.enums.Status;
import by.lawaksoft.tradebot.exception.dto.BusinessException;
import by.lawaksoft.tradebot.exception.dto.enums.ERROR_MESSAGE;
import by.lawaksoft.tradebot.service.api.trade.TradeService;
import by.lawaksoft.tradebot.service.entity.TradeOrderService;
import by.lawaksoft.tradebot.service.util.CreateTradeMessageService;
import by.lawaksoft.tradebot.util.TimeManager;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static by.lawaksoft.tradebot.mapper.OrderMapper.*;

@Service
public class TradeServiceImpl implements TradeService {

    private final TradeOrderService orderService;
    private final CreateTradeMessageService createTradeMessageService;
    private final OkxConfigSecurity okxConfigSecurity;
    private final SecurityService securityService;
    private final TradeClient tradeClient;

    @Autowired
    public TradeServiceImpl(TradeOrderService orderService, CreateTradeMessageService createTradeMessageService, OkxConfigSecurity okxConfigSecurity, SecurityService securityService, TradeClient tradeClient) {
        this.orderService = orderService;
        this.createTradeMessageService = createTradeMessageService;
        this.okxConfigSecurity = okxConfigSecurity;
        this.securityService = securityService;
        this.tradeClient = tradeClient;
    }

    @Override
    public GetOrderResponseDTO placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO) {
        User user = securityService.getUser();

        Order orderMap = mapPlaceOrderRequestDTOToOrder(placeOrderRequestDTO);

        OrderResponseDTO orderResponseDTO;
        try {
            orderResponseDTO = tradeClient.placeOrder(placeOrderRequestDTO, getHeaderForPlaceOrder(placeOrderRequestDTO,
                    TimeManager.getTimestampForOkx()));
        } catch (FeignException e) {
            throw new BusinessException(String.format("Bad feign request %s", e.getMessage()), ERROR_MESSAGE.BAD_REQUEST);
        }

        orderMap.setClientOrderId(orderResponseDTO.getData().get(0).getClOrdId());
        orderMap.setOrderId(orderResponseDTO.getData().get(0).getOrdId());
        orderMap.setTag(orderResponseDTO.getData().get(0).getTag());
        orderMap.setUser(user);
        orderMap.setStatus(Status.ACTIVE);

        Order orderDb = orderService.save(orderMap);
        return mapOrderToGetOrderResponseDTO(orderDb);
    }

    @Override
    public GetOrderDetailsDTO getOrderDetails(String instrumentId, String orderId, String clientOrderId) {
        User user = securityService.getUser();

        if (orderId.isBlank() && clientOrderId.isBlank()) {
            throw new BusinessException("Order id and client order id cant be empty");
        }
        OrderDetailsResponseDTO orderDetailsResponseDTO;
        try {
            orderDetailsResponseDTO = tradeClient.getOrderDetails(instrumentId, orderId, clientOrderId,
                                                                        getHeaderForOrderDetails(instrumentId, orderId,
                                                                                clientOrderId, TimeManager.getTimestampForOkx()));
        } catch (FeignException e) {
            throw new BusinessException(String.format("Bad feign request %s", e.getMessage()), ERROR_MESSAGE.BAD_REQUEST);
        }

        Order orderDb = orderService.findOrderByOrderIdAndUserId(orderDetailsResponseDTO.getOrdId(), user.getId());

        Order order = mapOrderDetailsResponseDTOToOrder(orderDetailsResponseDTO);
        order.setUser(user);
        order.setOrderId(orderDb.getOrderId());
        order.setId(orderDb.getId());

        Order updateOrder = orderService.save(order);
        return mapOrderToGetOrderDetailsDTO(updateOrder);
    }

    @Override
    public GetOrderResponseDTO cancelOrder(CancelOrderRequestDTO cancelOrderRequestDTO) {
        User user = securityService.getUser();

        if (cancelOrderRequestDTO.getClOrdId().isBlank() && cancelOrderRequestDTO.getOrdId().isBlank()) {
            throw new BusinessException("Order id and client order id cant be empty");
        }
        OrderResponseDTO orderResponseDTO;
        try {
            orderResponseDTO = tradeClient.cancelOrder(cancelOrderRequestDTO, getHeaderForCancelOrder(cancelOrderRequestDTO,
                                                                                                        TimeManager.getTimestampForOkx()));
        } catch (FeignException e) {
            throw new BusinessException(String.format("Bad feign request %s", e.getMessage()), ERROR_MESSAGE.BAD_REQUEST);
        }

        Order order = orderService.findOrderByOrderIdAndUserId(orderResponseDTO.getData().get(0).getOrdId(), user.getId());
        order.setStatus(Status.CANCELED);
        Order orderDb = orderService.save(order);
        return mapOrderToGetOrderResponseDTO(orderDb);
    }

    @Override
    public GetOrderResponseDTO amendOrder(AmendOrderRequestDTO amendOrderRequestDTO) {
        User user = securityService.getUser();
        checkAmendOrderReqFields(amendOrderRequestDTO);

        OrderResponseDTO orderResponseDTO;
        try {
            orderResponseDTO = tradeClient.amendOrder(amendOrderRequestDTO, getHeaderForAmendOrder(amendOrderRequestDTO,
                                                                                                    TimeManager.getTimestampForOkx()));
        } catch (FeignException e) {
            throw new BusinessException(String.format("Bad feign request %s", e.getMessage()), ERROR_MESSAGE.BAD_REQUEST);
        }

        Order orderDb = orderService.findOrderByOrderIdAndUserId(orderResponseDTO.getData().get(0).getOrdId(), user.getId());
        orderDb.setRequestId(orderResponseDTO.getData().get(0).getReqId());
        orderDb.setPrice(amendOrderRequestDTO.getNewPx());

        Order updateOrder = orderService.save(orderDb);
        return mapOrderToGetOrderResponseDTO(updateOrder);
    }


    private Map<String, String> getHeaderForCancelOrder(CancelOrderRequestDTO cancelOrderRequestDTO, String timestamp) {
        String message = createTradeMessageService.cancelOrderMessage(cancelOrderRequestDTO, timestamp);
        return okxConfigSecurity.getHeader(message, timestamp);
    }

    private Map<String, String> getHeaderForPlaceOrder(PlaceOrderRequestDTO placeOrderRequestDTO, String timestamp) {
        String message = createTradeMessageService.placeOrderMessage(placeOrderRequestDTO, timestamp);
        return okxConfigSecurity.getHeader(message, timestamp);
    }

    private Map<String, String> getHeaderForOrderDetails(String instrumentId, String orderId, String clientOrderId, String timestamp) {
        String message = createTradeMessageService.getOrderDetailsMessage(instrumentId, orderId, clientOrderId, timestamp);
        return okxConfigSecurity.getHeader(message, timestamp);
    }

    private Map<String, String> getHeaderForAmendOrder(AmendOrderRequestDTO amendOrderRequestDTO, String timestamp) {
        String message = createTradeMessageService.amendOrderMessage(amendOrderRequestDTO, timestamp);
        return okxConfigSecurity.getHeader(message, timestamp);
    }

    private void checkAmendOrderReqFields(AmendOrderRequestDTO amendOrderRequestDTO) {
        if ((amendOrderRequestDTO.getClOrdId() == null || amendOrderRequestDTO.getClOrdId().isBlank()) &&
                (amendOrderRequestDTO.getOrdId() == null || amendOrderRequestDTO.getOrdId().isBlank())) {
            throw new BusinessException("Order id and client order id cant be empty");
        }
        if ((amendOrderRequestDTO.getNewSz() == null || amendOrderRequestDTO.getNewSz().isBlank()) && amendOrderRequestDTO.getNewPx() == 0) {
            throw new BusinessException("New quantity and new price cant be empty");
        }
    }
}
