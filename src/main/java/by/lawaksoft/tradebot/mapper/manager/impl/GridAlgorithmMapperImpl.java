package by.lawaksoft.tradebot.mapper.manager.impl;

import by.lawaksoft.tradebot.dto.botinfo.AlgorithmDto;
import by.lawaksoft.tradebot.dto.botinfo.GridAlgorithmDto;
import by.lawaksoft.tradebot.entity.enums.AlgorithmBot;
import by.lawaksoft.tradebot.mapper.manager.Algorithm;
import by.lawaksoft.tradebot.mapper.manager.AlgorithmMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Algorithm(AlgorithmBot.GRID)
public class GridAlgorithmMapperImpl implements AlgorithmMapper<GridAlgorithmDto> {

    @Override
    public GridAlgorithmDto toParametersDto(Map<String , String> parameters) {
        return null;
    }
}
