package by.lawaksoft.tradebot.service.impl;

import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.repository.OrderRepository;
import by.lawaksoft.tradebot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public boolean existOrderByInstIdAndSideAndPriceAndUser(String instId, String side, double price, long userId) {
        return orderRepository.existsByInstrumentIdAndOrderSideAndPriceAndUserId(instId, side, price, userId);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }
}
