package by.lawaksoft.tradebot.service.manager.impl;

import by.lawaksoft.tradebot.dto.manager.AlgoInstanceDto;
import by.lawaksoft.tradebot.entity.enums.AlgorithmBotType;
import by.lawaksoft.tradebot.mapper.manager.Algorithm;
import by.lawaksoft.tradebot.service.manager.AlgoService;
import org.springframework.stereotype.Service;

@Service
@Algorithm(AlgorithmBotType.GRID)
public class AlgoGridServiceImpl implements AlgoService {

    @Override
    public void execute(AlgoInstanceDto algoInstanceDto) {

    }
}
