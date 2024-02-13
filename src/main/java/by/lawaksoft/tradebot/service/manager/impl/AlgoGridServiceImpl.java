package by.lawaksoft.tradebot.service.manager.impl;

import by.lawaksoft.tradebot.dto.algorithm.AlgoLayerDto;
import by.lawaksoft.tradebot.dto.algorithm.OrderDto;
import by.lawaksoft.tradebot.dto.algorithm.OrderType;
import by.lawaksoft.tradebot.dto.manager.AlgoInstanceDto;
import by.lawaksoft.tradebot.entity.AlgoParam;
import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.entity.enums.AlgorithmBotType;
import by.lawaksoft.tradebot.entity.enums.NecessarySynchronization;
import by.lawaksoft.tradebot.mapper.manager.Algorithm;
import by.lawaksoft.tradebot.repository.OrderRepository;
import by.lawaksoft.tradebot.service.manager.AlgoService;
import by.lawaksoft.tradebot.service.price_reaction.PriceReactionService;
import by.lawaksoft.tradebot.service.storage.PriceStorage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Algorithm(AlgorithmBotType.GRID)
public class AlgoGridServiceImpl implements AlgoService {

    private final OrderRepository orderRepository;
    private final PriceStorage priceStorage;
    private final PriceReactionService priceReactionService;

    public AlgoGridServiceImpl(OrderRepository orderRepository, PriceStorage priceStorage, PriceReactionService priceReactionService) {

        this.orderRepository = orderRepository;
        this.priceStorage = priceStorage;
        this.priceReactionService = priceReactionService;
    }

    @Override
    public void execute(AlgoInstanceDto algoInstanceDto) {

        Map<String, AlgoParam>  algoSettingAndParams = getAlgoSettings(algoInstanceDto);
        String instId = algoInstanceDto.getInstrument().getInstrumentId();
        List<AlgoLayerDto> algoLayerDtos = getAlgoLayers(algoSettingAndParams);

        createOrdersByALgoLayers(algoLayerDtos);

        runAlgorithm(algoLayerDtos, instId);
    }

    @Scheduled(fixedDelay = 10000L)
    private void runAlgorithm(List<AlgoLayerDto> algoLayerDtos, String instId) {

        BigDecimal price = priceStorage.getPriceById(instId);

        algoLayerDtos.parallelStream().forEach(algoLayerDto ->
                priceReactionService.onPriceReaction(algoLayerDto, price)
        );
    }

    private void createOrdersByALgoLayers(List<AlgoLayerDto> algoLayerDtos) {

        algoLayerDtos.forEach(layers -> {
            createOrder(layers.getBuyOrder(), layers);
            createOrder(layers.getSellOrder(), layers);
        });
    }

    private void createOrder(OrderDto orderDto, AlgoLayerDto layers) {

        Order order = Order.builder()
                .price(orderDto.getPrice())
                .quantityToBuyOrSell(layers.getQuantity().doubleValue())
                .necessarySynchronization(orderDto.getOrderStatus())
                .build();
        orderRepository.save(order);
        orderDto.setOrderId(order.getId());
    }

    private List<AlgoLayerDto> getAlgoLayers(Map<String, AlgoParam> algoSettingAndParams) {

        List<AlgoLayerDto> algoLayerDtos = new ArrayList<>();
        BigDecimal lowerGridRange = new BigDecimal(algoSettingAndParams.get("lowerGridRange").getValue());
        BigDecimal upperGridRange = new BigDecimal(algoSettingAndParams.get("upperGridRange").getValue());
        BigDecimal startPriceForLayer = new BigDecimal(algoSettingAndParams.get("accountBalance").getValue());
        int stepsCounts = Integer.parseInt(algoSettingAndParams.get("stepsCounts").getValue());
        String instId = algoSettingAndParams.get("tradeMarketPare").getValue();

        BigDecimal stepPrice = upperGridRange.subtract(lowerGridRange).divideToIntegralValue(BigDecimal.valueOf(stepsCounts));

        for (int i = 0; i < stepsCounts; i++) {
            BigDecimal priceForSell = upperGridRange.subtract(stepPrice.multiply(BigDecimal.valueOf(i)));
            if (priceForSell.compareTo(lowerGridRange) > 0) {
                algoLayerDtos.add(createAlgoLayer(priceForSell, stepPrice, startPriceForLayer, instId));
            }
        }

        return algoLayerDtos;
    }

    private AlgoLayerDto createAlgoLayer(BigDecimal priceForSell, BigDecimal stepPrice, BigDecimal startPriceForLayer, String instId) {

        BigDecimal quantity = startPriceForLayer.divide(priceStorage.getPriceById(instId));
        return new AlgoLayerDto(new OrderDto(priceForSell, OrderType.SELL, NecessarySynchronization.DONE),
                new OrderDto(priceForSell.subtract(stepPrice) , OrderType.BUY, NecessarySynchronization.DONE), instId, startPriceForLayer, quantity);
    }

    private Map<String, AlgoParam>  getAlgoSettings(AlgoInstanceDto algoInstanceDto) {

        return algoInstanceDto.getParameters().parallelStream().collect(Collectors.toMap(param -> param.getAlgoSetting().getNameSetting(), param -> param));
    }
}
