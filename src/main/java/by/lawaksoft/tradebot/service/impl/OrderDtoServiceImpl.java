package by.lawaksoft.tradebot.service.impl;

import by.lawaksoft.tradebot.config.security.SecurityService;
import by.lawaksoft.tradebot.dto.order.GetOrderDetailsDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderResponseDTO;
import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.exception.entity.BusinessException;
import by.lawaksoft.tradebot.service.OrderDtoService;
import by.lawaksoft.tradebot.service.OrderService;
import by.lawaksoft.tradebot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static by.lawaksoft.tradebot.exception.entity.enums.ERROR_CODE.ORDER_NOT_FOUND;
import static by.lawaksoft.tradebot.mapper.OrderMapper.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class OrderDtoServiceImpl implements OrderDtoService {

    private final OrderService orderService;
    private final SecurityService securityService;
    private final UserService userService;

    @Autowired
    public OrderDtoServiceImpl(OrderServiceImpl orderService, SecurityService securityService, UserService userService) {
        this.orderService = orderService;
        this.securityService = securityService;
        this.userService = userService;
    }

    @Override
    public GetOrderResponseDTO placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO) {
        User user = userService.getUserById(getUser().getId());
        Order orderMap = mapPlaceOrderRequestDTOToOrder(placeOrderRequestDTO);
        if (!orderService.existOrderByInstIdAndSideAndPriceAndUser(orderMap.getInstrumentId(), orderMap.getOrderSide(),
                                                                        orderMap.getPrice(), user.getId())) {
            throw new BusinessException(String.format("Order with instrumentId %s and user id %s not found",
                                                orderMap.getInstrumentId(), user.getId()), ORDER_NOT_FOUND, NOT_FOUND);
        }

        //добавить проверку доступного количества средств на создание заказа
        //добавить обращние к api и получение статуса

        Order orderDb = orderService.create(orderMap);
        return mapOrderToGetOrderResponseDTO(orderDb);
    }

    @Override
    public GetOrderDetailsDTO getOrderDetails(String instrumentId, String orderId, String clientOrderId) {
        User user = userService.getUserById(getUser().getId());

        //добавить обращние к api и получение деталей
        OrderDetailsResponseDTO orderApi = new OrderDetailsResponseDTO();
        Order order = mapOrderDetailsResponseDTOToOrder(orderApi);
        order.setUser(user);
        Order orderDb = orderService.update(order);
        return mapOrderToGetOrderDetailsDTO(orderDb);
    }


    private User getUser() {
        return securityService.getUser();
    }
}
