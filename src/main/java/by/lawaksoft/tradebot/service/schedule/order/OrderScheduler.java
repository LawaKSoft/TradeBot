package by.lawaksoft.tradebot.service.schedule.order;

import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.entity.AlgoParam;
import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.entity.enums.NecessarySynchronization;
import by.lawaksoft.tradebot.entity.enums.Status;
import by.lawaksoft.tradebot.mapper.OrderMapper;
import by.lawaksoft.tradebot.service.entity.AlgoInstanceService;
import by.lawaksoft.tradebot.service.entity.TradeOrderService;
import by.lawaksoft.tradebot.service.user.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderScheduler {

    private final TradeOrderService tradeOrderService;
    private final AlgoInstanceService algoInstanceService;
    private final OrderService orderService;

    private static final String ACTIVE_STATUS_FROM_STOCK = "filled";
    private static final String CANCELED_STATUS_FROM_STOCK = "canceled";

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
                            .getStockOrdersByAlgoSettingsAndInstrumentId(algoSettings, instrumentId);
                    return Flux.fromIterable(ordersDb)
                            .flatMap(order -> {
                                Mono<OrderDetailsResponseDTO> matchingOrder = Flux.fromIterable(ordersStock)
                                        .filter(orderDet -> orderDet.getInstId().equals(order.getInstrumentId()))
                                        .next();

                                return matchingOrder
                                        .map(orderMatch -> {
                                            if (order.getNecessarySynchronization().equals(NecessarySynchronization.UPDATED) &&
                                                    orderMatch.getState().equals(ACTIVE_STATUS_FROM_STOCK)) {
                                                tradeOrderService.updateOrderByAlgoSettings(algoSettings, order);
                                                //TODO: DONE или WAITING
                                                order.setNecessarySynchronization(NecessarySynchronization.DONE);
                                                orderService.save(order);
                                            }
                                            if (order.getNecessarySynchronization().equals(NecessarySynchronization.CLOSED) &&
                                                    orderMatch.getState().equals(ACTIVE_STATUS_FROM_STOCK)) {
                                                tradeOrderService.closedOrderByAlgoSettings(algoSettings, order);
                                                order.setNecessarySynchronization(NecessarySynchronization.CANCELED);
                                                orderService.save(order);
                                            }
                                            return orderMatch;
                                        })
                                        .switchIfEmpty(Mono.defer(() -> {
                                            if (order.getNecessarySynchronization().equals(NecessarySynchronization.ACCEPT)) {
                                                tradeOrderService.saveOrderByAlgoSettings(algoSettings, order);
                                                order.setNecessarySynchronization(NecessarySynchronization.DONE);
                                                orderService.save(order);
                                            }
                                            return Mono.empty();
                                        }));
                            })
                            .thenMany(Flux.fromIterable(ordersStock)
                                    .filter(ordStock -> ordersDb.stream()
                                            .noneMatch(ord -> ord.getInstrumentId().equals(ordStock.getInstId())))
                                    .flatMap(ordStock -> {
                                        if (ordStock.getState().equals(ACTIVE_STATUS_FROM_STOCK)) {
                                            //TODO: Какой статус, когда пришло из OKX
                                            Order order = OrderMapper.mapOrderDetailsResponseDTOToOrder(ordStock);
                                            order.setNecessarySynchronization(NecessarySynchronization.WAITING);
                                            order.setStatus(Status.ACTIVE);
                                            orderService.save(order);
                                        }
                                        return Mono.empty();
                                    }))
                            .then();
                });
    }
}