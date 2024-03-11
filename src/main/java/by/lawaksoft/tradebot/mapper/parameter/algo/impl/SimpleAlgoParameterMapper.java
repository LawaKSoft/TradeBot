package by.lawaksoft.tradebot.mapper.parameter.algo.impl;

import by.lawaksoft.tradebot.dto.botinfo.SimpleBotParametersDto;
import by.lawaksoft.tradebot.entity.enums.AlgorithmType;
import by.lawaksoft.tradebot.mapper.parameter.algo.AlgoParameter;
import by.lawaksoft.tradebot.mapper.parameter.algo.AlgoParameterMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
@AlgoParameter(AlgorithmType.SIMPLE)
public class SimpleAlgoParameterMapper implements AlgoParameterMapper<SimpleBotParametersDto> {

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
