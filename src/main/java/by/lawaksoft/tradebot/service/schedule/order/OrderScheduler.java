package by.lawaksoft.tradebot.service.schedule.order;

import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.entity.AlgoParam;
import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.service.entity.AlgoInstanceService;
import by.lawaksoft.tradebot.service.entity.TradeOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderScheduler {

    private final TradeOrderService tradeOrderService;
    private final AlgoInstanceService algoInstanceService;

    @Async
    @Scheduled(fixedDelay = 10000L)
    @Transactional
    public void synchronizedOrders() {
        Long userId = 1L;
        String instrumentId = "BTC-USDT-1234";

        //Map<Map<nameSettings, value>, List<Order (necessary - Active||Canceled)>>
        Map<Map<String, String>, List<Order>> algoParamsNOrdersMap = algoInstanceService
                .findAllByUserIdAndInstrumentId(userId, instrumentId).stream()
                .collect(Collectors.toMap(
                        algoInstance -> algoInstance.getParameters().stream()
                                .collect(Collectors.toMap(
                                        algoParam -> algoParam.getAlgoSetting().getNameSetting(),
                                        AlgoParam::getValue
                                )),
                        algoInstance -> tradeOrderService.findAllByAlgoInstIdAndNecessarySynch(algoInstance.getId())
                ));

        Flux.fromIterable(algoParamsNOrdersMap.entrySet())
                .flatMap(entry -> {
                    Map<String, String> algoSettings = entry.getKey();
                    List<Order> ordersDb = entry.getValue();
                    List<OrderDetailsResponseDTO> ordersStock = tradeOrderService
                            .getStockOrdersByAlgoSettingsAndInstrumentsIds(algoSettings, ordersDb.stream()
                                                                                            .map(Order::getInstrumentId)
                                                                                            .toList());
                    //TODO: Добавить логику сравнения и синхронизации
                    return null;
                });
    }
}
