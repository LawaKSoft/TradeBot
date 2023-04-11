package by.lawaksoft.tradebot.mapper.manager;

import by.lawaksoft.tradebot.dto.botinfo.AlgorithmDto;

import java.util.Map;

public interface AlgorithmMapper <T extends AlgorithmDto>{

    T toParametersDto(Map<String,String> parameters);
}
