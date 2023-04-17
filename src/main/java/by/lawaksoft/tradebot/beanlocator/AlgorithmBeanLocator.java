package by.lawaksoft.tradebot.beanlocator;

import by.lawaksoft.tradebot.entity.enums.AlgorithmBotType;
import by.lawaksoft.tradebot.service.manager.AlgoService;
import org.springframework.stereotype.Component;

@Component
public interface AlgorithmBeanLocator {

    AlgoService getAlgo(AlgorithmBotType type);
}
