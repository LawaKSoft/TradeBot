package by.lawaksoft.tradebot.mapper.parameter.client.impl;

import by.lawaksoft.tradebot.dto.clientinfo.OkxClientParameterDto;
import by.lawaksoft.tradebot.entity.enums.ClientType;
import by.lawaksoft.tradebot.mapper.parameter.client.ClientParameter;
import by.lawaksoft.tradebot.mapper.parameter.client.ClientParameterMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@ClientParameter(ClientType.OKX)
public class OkxClientParameterMapper implements ClientParameterMapper<OkxClientParameterDto> {

	@Override
	public OkxClientParameterDto toParametersDto(Map<String, String> parameters) {

		return OkxClientParameterDto.builder()
				.okxApiKey(parameters.get("API_KEY"))
				.okxSecretKey(parameters.get("SECRET_KEY"))
				.okxPassphrase(parameters.get("PASSPHRASE"))
				.build();
	}
}
