package by.lawaksoft.tradebot.service.entity.impl;

import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.exception.entity.BusinessException;
import by.lawaksoft.tradebot.exception.entity.enums.ERROR_CODE;
import by.lawaksoft.tradebot.repository.OrderRepository;
import by.lawaksoft.tradebot.service.entity.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order findOrderByOrderIdAndUserId(String orderId, long userId) {
        return orderRepository.findByOrderIdAndUserId(orderId, userId)
                .orElseThrow(() -> new BusinessException(String.format("Order with orderId %s and userId %s not found", orderId, userId),
                                                                    ERROR_CODE.ORDER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }
}
