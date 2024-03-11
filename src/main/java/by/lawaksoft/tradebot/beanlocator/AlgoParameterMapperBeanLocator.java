package by.lawaksoft.tradebot.beanlocator;

import by.lawaksoft.tradebot.dto.botinfo.BotParametersDto;
import by.lawaksoft.tradebot.entity.enums.AlgorithmType;
import by.lawaksoft.tradebot.mapper.parameter.algo.AlgoParameterMapper;
import org.springframework.stereotype.Component;

@Component
public interface AlgoParameterMapperBeanLocator {

	<T extends BotParametersDto> AlgoParameterMapper<T> getMapper(AlgorithmType type);
}