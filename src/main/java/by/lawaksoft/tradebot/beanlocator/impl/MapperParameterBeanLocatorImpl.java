package by.lawaksoft.tradebot.beanlocator.impl;

import by.lawaksoft.tradebot.beanlocator.MapperParameterBeanLocator;
import by.lawaksoft.tradebot.dto.botinfo.BotParametersDto;
import by.lawaksoft.tradebot.entity.enums.AlgorithmType;
import by.lawaksoft.tradebot.mapper.parameter.AlgoParameterMapper;
import by.lawaksoft.tradebot.mapper.parameter.ParameterMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class MapperParameterBeanLocatorImpl implements MapperParameterBeanLocator {

	private final Map<AlgorithmType, ParameterMapper<?>> mappers = new HashMap<>();

	@EventListener
	public void mapperBeanListener(ContextRefreshedEvent event) {

		ApplicationContext context = event.getApplicationContext();
		Map<String, Object> beanWithAnnotation = context.getBeansWithAnnotation(AlgoParameterMapper.class);
		for (Object obj : beanWithAnnotation.values()) {
			AlgorithmType algorithmType = obj.getClass().getAnnotation(AlgoParameterMapper.class).value();
			mappers.put(algorithmType, (ParameterMapper<?>) obj);
		}
	}

	@Override
	public <T extends BotParametersDto> ParameterMapper<T> getMapper(AlgorithmType type) {

		var mapper = mappers.get(type);
		return Objects.nonNull(mapper) ? (ParameterMapper<T>) mapper : null;
	}
}
