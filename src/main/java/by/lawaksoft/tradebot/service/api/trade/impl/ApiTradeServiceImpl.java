package by.lawaksoft.tradebot.service.api.trade.impl;

import by.lawaksoft.tradebot.client.TradeClient;
import by.lawaksoft.tradebot.config.security.OkxConfigSecurity;
import by.lawaksoft.tradebot.config.security.SecurityService;
import by.lawaksoft.tradebot.dto.ResponseDTO;
import by.lawaksoft.tradebot.dto.order.*;
import by.lawaksoft.tradebot.dto.order.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.entity.enums.Status;
import by.lawaksoft.tradebot.exception.dto.BusinessException;
import by.lawaksoft.tradebot.exception.dto.enums.ERROR_MESSAGE;
import by.lawaksoft.tradebot.service.api.trade.ApiTradeService;
import by.lawaksoft.tradebot.service.entity.TradeOrderService;
import by.lawaksoft.tradebot.service.util.CreateTradeMessageService;
import by.lawaksoft.tradebot.util.TimeManager;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static by.lawaksoft.tradebot.mapper.OrderMapper.*;

@Service
public class ApiTradeServiceImpl implements ApiTradeService {

    private final TradeOrderService orderService;
    private final CreateTradeMessageService createTradeMessageService;
    private final OkxConfigSecurity okxConfigSecurity;
    private final SecurityService securityService;
    private final TradeClient tradeClient;

    private static final String BAD_FEIGN_REQUEST = "Bad feign request %s";
    private static final String ORD_N_CLO_IDS_CANT_BE_EMPTY = "Order id and client order id cant be empty";
    private static final String NEW_QUAN_N_PRICE_CANT_BE_EMPTY = "New quantity and new price cant be empty";
    private static final String INSTRUMENT_TYPE_SPOT = "SPOT";
    @Autowired
    public ApiTradeServiceImpl(TradeOrderService orderService, CreateTradeMessageService createTradeMessageService,
                               OkxConfigSecurity okxConfigSecurity, SecurityService securityService, TradeClient tradeClient) {
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

        ResponseDTO<OrderDataResponseDTO> orderResponseDTO;
        try {
            orderResponseDTO = tradeClient.placeOrder(placeOrderRequestDTO, getHeaderForPlaceOrder(placeOrderRequestDTO,
                    TimeManager.getTimestampForOkx()));
        } catch (FeignException e) {
            throw new BusinessException(String.format(BAD_FEIGN_REQUEST, e.getMessage()), ERROR_MESSAGE.BAD_REQUEST);
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

        if ((orderId == null || orderId.isBlank()) && (clientOrderId == null || clientOrderId.isBlank())) {
            throw new BusinessException();
        }

        OrderDetailsResponseDTO orderDetailsResponseDTO;
        try {
            if (orderId != null) {
                orderDetailsResponseDTO = tradeClient.getOrderDetailsByOrderId(instrumentId, orderId,
                        getHeaderForOrderDetailsByOrderId(instrumentId, orderId, TimeManager.getTimestampForOkx()));
            } else {
                orderDetailsResponseDTO = tradeClient.getOrderDetailsByClientOrderId(instrumentId, clientOrderId,
                        getHeaderForOrderDetailsByClientOrderId(instrumentId, clientOrderId, TimeManager.getTimestampForOkx()));
            }
        } catch (FeignException e) {
            throw new BusinessException(String.format(BAD_FEIGN_REQUEST, e.getMessage()), ERROR_MESSAGE.BAD_REQUEST);
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
            throw new BusinessException(ORD_N_CLO_IDS_CANT_BE_EMPTY);
        }
        ResponseDTO<OrderDataResponseDTO> orderResponseDTO;
        try {
            orderResponseDTO = tradeClient.cancelOrder(cancelOrderRequestDTO, getHeaderForCancelOrder(cancelOrderRequestDTO,
                    TimeManager.getTimestampForOkx()));
        } catch (FeignException e) {
            throw new BusinessException(String.format(BAD_FEIGN_REQUEST, e.getMessage()), ERROR_MESSAGE.BAD_REQUEST);
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

        ResponseDTO<OrderDataResponseDTO> orderResponseDTO;
        try {
            orderResponseDTO = tradeClient.amendOrder(amendOrderRequestDTO, getHeaderForAmendOrder(amendOrderRequestDTO,
                    TimeManager.getTimestampForOkx()));
        } catch (FeignException e) {
            throw new BusinessException(String.format(BAD_FEIGN_REQUEST, e.getMessage()), ERROR_MESSAGE.BAD_REQUEST);
        }

        Order orderDb = orderService.findOrderByOrderIdAndUserId(orderResponseDTO.getData().get(0).getOrdId(), user.getId());
        orderDb.setRequestId(orderResponseDTO.getData().get(0).getReqId());
        orderDb.setPrice(amendOrderRequestDTO.getNewPx());

        Order updateOrder = orderService.save(orderDb);
        return mapOrderToGetOrderResponseDTO(updateOrder);
    }

    @Override
    public List<OrderDetailsResponseDTO> getOrdersHistoryByAlgoParamsAndInstrumentId(Map<String, String> algoParams, String instrumentId) {
        ResponseDTO<OrderDetailsResponseDTO> responseOrderDetailsDto =
                tradeClient.getOrderHistoryForWeekByInstrument(instrumentId, INSTRUMENT_TYPE_SPOT,
                        getHeaderForOrderHistoryByAlgoParamsAndInstrumentId(algoParams, instrumentId, TimeManager.getTimestampForOkx()));
        return responseOrderDetailsDto.getData();
    }

    @Override
    public List<OrderDetailsResponseDTO> getOrdersHistoryByAlgoParamsAndInstrumentId(Map<String, String> algoParams, List<String> instrumentId) {
        ResponseDTO<OrderDetailsResponseDTO> responseOrderDetailsDto =
                tradeClient.getOrderHistoryForWeekByInstruments(INSTRUMENT_TYPE_SPOT, instrumentId.toArray(new String[0]),
                        getHeaderForOrderHistoryByAlgoParamsAndInstrumentsIds(algoParams, instrumentId, TimeManager.getTimestampForOkx()));
        return responseOrderDetailsDto.getData();
    }

    private Map<String, String> getHeaderForOrderHistoryByAlgoParamsAndInstrumentsIds(Map<String, String> algoParams, List<String> instrumentIds, String timestamp) {
        String message = createTradeMessageService.getHistoryForWeekByInstrumentsIds(instrumentIds, timestamp);
        return okxConfigSecurity.getHeader(message, timestamp, algoParams);
    }

    private Map<String, String> getHeaderForOrderHistoryByAlgoParamsAndInstrumentId(Map<String, String> algoParams, String instrumentId, String timestamp) {
        String message = createTradeMessageService.getHistoryForWeekByInstrumentId(instrumentId, timestamp);
        return okxConfigSecurity.getHeader(message, timestamp, algoParams);
    }

    private Map<String, String> getHeaderForCancelOrder(CancelOrderRequestDTO cancelOrderRequestDTO, String timestamp) {
        String message = createTradeMessageService.cancelOrderMessage(cancelOrderRequestDTO, timestamp);
        return okxConfigSecurity.getHeader(message, timestamp);
    }

    private Map<String, String> getHeaderForPlaceOrder(PlaceOrderRequestDTO placeOrderRequestDTO, String timestamp) {
        String message = createTradeMessageService.placeOrderMessage(placeOrderRequestDTO, timestamp);
        return okxConfigSecurity.getHeader(message, timestamp);
    }

    private Map<String, String> getHeaderForOrderDetailsByOrderId(String instrumentId, String orderId, String timestamp) {
        String message = createTradeMessageService.getOrderDetailsByOrderIdMessage(instrumentId, orderId, timestamp);
        return okxConfigSecurity.getHeader(message, timestamp);
    }

    private Map<String, String> getHeaderForOrderDetailsByClientOrderId(String instrumentId, String clientOrderId, String timestamp) {
        String message = createTradeMessageService.getOrderDetailsByClientOrderIdMessage(instrumentId, clientOrderId, timestamp);
        return okxConfigSecurity.getHeader(message, timestamp);
    }

    private Map<String, String> getHeaderForAmendOrder(AmendOrderRequestDTO amendOrderRequestDTO, String timestamp) {
        String message = createTradeMessageService.amendOrderMessage(amendOrderRequestDTO, timestamp);
        return okxConfigSecurity.getHeader(message, timestamp);
    }

    private void checkAmendOrderReqFields(AmendOrderRequestDTO amendOrderRequestDTO) {
        if ((amendOrderRequestDTO.getClOrdId() == null || amendOrderRequestDTO.getClOrdId().isBlank()) &&
                (amendOrderRequestDTO.getOrdId() == null || amendOrderRequestDTO.getOrdId().isBlank())) {
            throw new BusinessException(ORD_N_CLO_IDS_CANT_BE_EMPTY);
        }
        if ((amendOrderRequestDTO.getNewSz() == null || amendOrderRequestDTO.getNewSz().isBlank()) && amendOrderRequestDTO.getNewPx().signum() == 0) {
            throw new BusinessException(NEW_QUAN_N_PRICE_CANT_BE_EMPTY);
        }
    }
}
