package by.lawaksoft.tradebot.service;

import by.lawaksoft.tradebot.entity.Order;

public interface OrderService {

    Order create(Order order);
    boolean existOrderByInstIdAndSideAndPriceAndUser(String instId, String side, double price, long userId);

    Order update(Order order);
}
