package by.lawaksoft.tradebot.service.entity;

import by.lawaksoft.tradebot.entity.Order;

public interface OrderService {

    Order create(Order order);
    boolean existOrderByInstIdAndSideAndPriceAndUser(String instId, long userId);

    Order update(Order order);
    Order findOrderByOrderIdAndUserId(String orderId, long userId);
}
