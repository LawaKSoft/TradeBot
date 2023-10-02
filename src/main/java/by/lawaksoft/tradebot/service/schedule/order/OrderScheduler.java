package by.lawaksoft.tradebot.service.schedule.order;

import by.lawaksoft.tradebot.client.TradeClient;
import by.lawaksoft.tradebot.config.security.OkxConfigSecurity;
import by.lawaksoft.tradebot.dto.ResponseDTO;
import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.entity.enums.Status;
import by.lawaksoft.tradebot.exception.InstrumentsException;
import by.lawaksoft.tradebot.service.entity.TradeOrderService;
import by.lawaksoft.tradebot.service.util.CreateTradeMessageService;
import by.lawaksoft.tradebot.util.TimeManager;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderScheduler {

    private final TradeOrderService tradeOrderService;
    private final TradeClient tradeClient;
    private final OkxConfigSecurity okxConfigSecurity;
    private final CreateTradeMessageService createTradeMessageService;

    @Async
    @Scheduled(fixedDelay = 10000L)
    public void synchronizedOrders() {
        try {
            Long userId = 1L;
            List<Order> ordersDb = tradeOrderService.findAllDistinctInstrumentsByUserId(userId);
            String[] instIds = ordersDb.stream().map(Order::getInstrumentId).toArray(String[]::new);
            String timestamp = TimeManager.getTimestampForOkx();
            ResponseDTO<OrderDetailsResponseDTO> ordersFs =
                    tradeClient.getOrderHistoryForWeek(instIds, "SPOT",
                            okxConfigSecurity.getHeader(createTradeMessageService.getHistoryForWeek(instIds, timestamp), timestamp));
            Set<String> canceledOrders = ordersFs.getData().stream()
                    .filter(orderF -> orderF.getState().equals("canceled"))
                    .map(OrderDetailsResponseDTO::getInstId)
                    .collect(Collectors.toSet());

            List<Order> canceled = ordersDb.stream()
                    .filter(order -> canceledOrders.contains(order.getInstrumentId()) && order.isReduceOnly())
                    .peek(order -> order.setStatus(Status.CANCELED))
                    .toList();

            tradeOrderService.saveAll(canceled);
        } catch (FeignException | InstrumentsException e) {
            log.warn(e.getMessage());
        }
    }
}
