package by.lawaksoft.tradebot.mapper.parameter;

import by.lawaksoft.tradebot.dto.botinfo.BotParametersDto;
import by.lawaksoft.tradebot.dto.botinfo.SimpleBotParametersDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ParameterMapper<T extends BotParametersDto> {

	T toParametersDto(Map<String,String> parameters);
}
