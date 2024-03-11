package by.lawaksoft.tradebot.service.user.impl;

import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.entity.enums.NecessarySynchronization;
import by.lawaksoft.tradebot.repository.OrderRepository;
import by.lawaksoft.tradebot.service.user.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public void updateOrderNessSynchByInstrumentId(String instrumentId, NecessarySynchronization necessarySynchronization) {
        orderRepository.updateNecessarySynchronizationByInstrumentId(necessarySynchronization.toString(), instrumentId);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

}
