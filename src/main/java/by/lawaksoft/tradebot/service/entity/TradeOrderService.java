package by.lawaksoft.tradebot.service.entity;

import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.exception.InstrumentsException;

import java.util.List;

public interface TradeOrderService {

    Order save(Order order);
    Order findOrderByOrderIdAndUserId(String orderId, long userId);

    List<Order> findAllDistinctInstrumentsByUserId(long userId) throws InstrumentsException;

    List<Order> saveAll(List<Order> canceled);
}
