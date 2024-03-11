package by.lawaksoft.tradebot.service.user;

import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.entity.enums.NecessarySynchronization;

public interface OrderService {

    void updateOrderNessSynchByInstrumentId(String instrumentId, NecessarySynchronization necessarySynchronization);

    Order save(Order order);
}
