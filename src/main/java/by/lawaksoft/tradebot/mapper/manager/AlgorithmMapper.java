package by.lawaksoft.tradebot.mapper.manager;

import by.lawaksoft.tradebot.dto.botinfo.AlgorithmDto;
import by.lawaksoft.tradebot.mapper.parameter.ParameterMapper;

import java.util.Map;

public interface AlgorithmMapper <T extends AlgorithmDto> {

    T toParametersDto(Map<String,String> parameters);
}
