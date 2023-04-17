package by.lawaksoft.tradebot.mapper.parameter.client;


import by.lawaksoft.tradebot.dto.clientinfo.ClientParametersDto;

import java.util.Map;

public interface ClientParameterMapper <T extends ClientParametersDto> {

	T toParametersDto(Map<String,String> parameters);
}
