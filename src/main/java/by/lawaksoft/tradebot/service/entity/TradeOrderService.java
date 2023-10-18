package by.lawaksoft.tradebot.service.entity;

import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.entity.Order;

import java.util.List;
import java.util.Map;

public interface TradeOrderService {

    Order save(Order order);

    Order findOrderByOrderIdAndUserId(String orderId, long userId);

    List<Order> saveAll(List<Order> canceled);

    List<Order> findAllByAlgoInstIdAndNecessarySynch(Long algoId);

    List<OrderDetailsResponseDTO> getStockOrdersByAlgoSettingsAndInstrumentsIds(Map<String, String> algoSettings, List<String> instrumentsIds);

    boolean synchronizedOrdersDbAndStockOrders(List<Order> ordersDb, List<OrderDetailsResponseDTO> ordersStock);
}
