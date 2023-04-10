package by.lawaksoft.tradebot.mapper.parameter.algo;

import by.lawaksoft.tradebot.dto.botinfo.BotParametersDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface AlgoParameterMapper<T extends BotParametersDto> {

	T toParametersDto(Map<String,String> parameters);
}
