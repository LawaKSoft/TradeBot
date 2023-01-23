package by.lawaksoft.tradebot.service.entity.impl;

import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.service.entity.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

//    private final OrderRepository orderRepository;
//
//    @Autowired
//    public OrderServiceImpl(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

    @Override
    public Order create(Order order) {
        return null;
//        return orderRepository.save(order);
    }

    @Override
    public boolean existOrderByInstIdAndSideAndPriceAndUser(String instId, long userId) {
        return false;
    }

    @Override
    public Order update(Order order) {
        return null;
//        return orderRepository.save(order);
    }

    public Order findOrderByOrderIdAndUserId(String orderId, long userId) {
        return null;
    }
}
