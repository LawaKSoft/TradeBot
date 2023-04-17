package by.lawaksoft.tradebot.service.manager.impl;

import by.lawaksoft.tradebot.dto.AlgoInstanceDto;
import by.lawaksoft.tradebot.entity.enums.AlgorithmBot;
import by.lawaksoft.tradebot.mapper.manager.Algorithm;
import by.lawaksoft.tradebot.service.manager.AlgoService;
import org.springframework.stereotype.Service;

@Service
@Algorithm(AlgorithmBot.GRID)
public class AlgoGridServiceImpl implements AlgoService {

    @Override
    public void execute(AlgoInstanceDto algoInstanceDto) {

    }
}
