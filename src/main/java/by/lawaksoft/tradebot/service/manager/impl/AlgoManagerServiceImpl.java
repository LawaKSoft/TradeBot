package by.lawaksoft.tradebot.service.manager.impl;

import by.lawaksoft.tradebot.beanlocator.AlgorithmBeanLocator;
import by.lawaksoft.tradebot.dto.manager.AlgoInstanceDto;
import by.lawaksoft.tradebot.entity.enums.AlgorithmBotType;
import by.lawaksoft.tradebot.service.manager.AlgoManagerService;
import by.lawaksoft.tradebot.service.manager.AlgoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlgoManagerServiceImpl implements AlgoManagerService {

    private final AlgorithmBeanLocator algorithmBeanLocator;

    @Override
    public void run(AlgoInstanceDto algoInstance) {

        var algoName = algoInstance.getAlgoType().getName();
        AlgoService algo = algorithmBeanLocator.getAlgo(AlgorithmBotType.byText(algoName));
        algo.execute(algoInstance);
    }
}
