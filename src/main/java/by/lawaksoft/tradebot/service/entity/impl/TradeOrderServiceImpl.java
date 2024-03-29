package by.lawaksoft.tradebot.service.entity.impl;

import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.exception.dto.BusinessException;
import by.lawaksoft.tradebot.exception.dto.enums.ERROR_MESSAGE;
import by.lawaksoft.tradebot.repository.OrderRepository;
import by.lawaksoft.tradebot.service.api.trade.ApiTradeService;
import by.lawaksoft.tradebot.service.entity.TradeOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class TradeOrderServiceImpl implements TradeOrderService {

    private final OrderRepository orderRepository;
    private final ApiTradeService apiTradeService;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findOrderByOrderIdAndUserId(String orderId, long userId) {
        return orderRepository.findByOrderIdAndUserId(orderId, userId)
                .orElseThrow(() -> new BusinessException(String.format("Order with orderId %s and userId %s not found", orderId, userId),
                        ERROR_MESSAGE.ORDER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Order> saveAll(List<Order> canceled) {
        return orderRepository.saveAll(canceled);
    }

    @Override
    public List<Order> findAllByAlgoInstIdAndNecessarySynch(Long algoId) {
        return orderRepository.findAllByAlgoInstanceIdAndNecessarySynchronizationIn(algoId)
                .orElse(new ArrayList<>());
    }

    @Override
    public List<OrderDetailsResponseDTO> getStockOrdersByAlgoSettingsAndInstrumentId(Map<String, String> algoParams, String instrumentId) {
        return apiTradeService.getOrdersHistoryByAlgoParamsAndInstrumentId(algoParams, instrumentId);
    }

    @Override
    public void updateOrderByAlgoSettings(Map<String, String> algoSettings, Order order) {

    }

    @Override
    public void saveOrderByAlgoSettings(Map<String, String> algoSettings, Order order) {

    }

    @Override
    public void closedOrderByAlgoSettings(Map<String, String> algoSettings, Order order) {

    }
}
