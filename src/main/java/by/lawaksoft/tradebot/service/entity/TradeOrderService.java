package by.lawaksoft.tradebot.service.entity;

import by.lawaksoft.tradebot.document.Order;

public interface TradeOrderService {

    Order save(Order order);
    Order findOrderByOrderIdAndUserId(String orderId, long userId);
}
