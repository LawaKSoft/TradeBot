package by.lawaksoft.tradebot.beanlocator.impl;

import by.lawaksoft.tradebot.beanlocator.AlgoParameterMapperBeanLocator;
import by.lawaksoft.tradebot.dto.botinfo.BotParametersDto;
import by.lawaksoft.tradebot.entity.enums.AlgorithmType;
import by.lawaksoft.tradebot.mapper.parameter.algo.AlgoParameter;
import by.lawaksoft.tradebot.mapper.parameter.algo.AlgoParameterMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AlgoParameterMapperBeanLocatorImpl implements AlgoParameterMapperBeanLocator {

	private final Map<AlgorithmType, AlgoParameterMapper<?>> mappers = new HashMap<>();

	@EventListener
	public void mapperBeanListener(ContextRefreshedEvent event) {

		ApplicationContext context = event.getApplicationContext();
		Map<String, Object> beanWithAnnotation = context.getBeansWithAnnotation(AlgoParameter.class);
		for (Object obj : beanWithAnnotation.values()) {
			AlgorithmType algorithmType = obj.getClass().getAnnotation(AlgoParameter.class).value();
			mappers.put(algorithmType, (AlgoParameterMapper<?>) obj);
		}
	}

	@Override
	public <T extends BotParametersDto> AlgoParameterMapper<T> getMapper(AlgorithmType type) {

		var mapper = mappers.get(type);
		return Objects.nonNull(mapper) ? (AlgoParameterMapper<T>) mapper : null;
	}
}
