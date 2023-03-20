package by.lawaksoft.tradebot.mapper.parameter.impl;

import by.lawaksoft.tradebot.dto.botinfo.SimpleBotParametersDto;
import by.lawaksoft.tradebot.entity.enums.AlgorithmType;
import by.lawaksoft.tradebot.mapper.parameter.AlgoParameterMapper;
import by.lawaksoft.tradebot.mapper.parameter.ParameterMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@AlgoParameterMapper(AlgorithmType.SIMPLE)
public class SimpleAlgoParameterMapper implements ParameterMapper<SimpleBotParametersDto> {

	@Override
	public SimpleBotParametersDto toParametersDto(Map<String, String> parameters) {

		return SimpleBotParametersDto.builder()
				.accountBalance(new BigDecimal(parameters.get("accountBalance")))
				.lowerGridRange(new BigDecimal(parameters.get("lowerGridRange")))
				.upperGridRange(new BigDecimal(parameters.get("upperGridRange")))
				.stepsCounts(new BigDecimal(parameters.get("stepsCounts")))
				.tradeMarketPare(parameters.get("tradeMarketPare"))
				.build();
	}
}
