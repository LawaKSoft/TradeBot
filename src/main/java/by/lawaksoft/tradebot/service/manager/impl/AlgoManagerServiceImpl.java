package by.lawaksoft.tradebot.service.manager.impl;

import by.lawaksoft.tradebot.beanlocator.AlgorithmBeanLocator;
import by.lawaksoft.tradebot.dto.AlgoInstanceDto;
import by.lawaksoft.tradebot.entity.AlgoInstance;
import by.lawaksoft.tradebot.entity.enums.AlgorithmBot;
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

        AlgoService algo = algorithmBeanLocator.getAlgo(AlgorithmBot.byText(algoName));
        algo.execute(algoInstance);
    }
}
