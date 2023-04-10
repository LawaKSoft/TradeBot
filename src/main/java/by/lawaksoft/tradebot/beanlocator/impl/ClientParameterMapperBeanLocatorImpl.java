package by.lawaksoft.tradebot.beanlocator.impl;

import by.lawaksoft.tradebot.beanlocator.ClientParameterMapperBeanLocator;
import by.lawaksoft.tradebot.dto.clientinfo.ClientParametersDto;
import by.lawaksoft.tradebot.entity.enums.ClientType;
import by.lawaksoft.tradebot.mapper.parameter.client.ClientParameter;
import by.lawaksoft.tradebot.mapper.parameter.client.ClientParameterMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class ClientParameterMapperBeanLocatorImpl implements ClientParameterMapperBeanLocator {

	private final Map<ClientType, ClientParameterMapper<?>> mappers = new HashMap<>();

	@EventListener
	public void mapperBeanListener(ContextRefreshedEvent event) {

		ApplicationContext context = event.getApplicationContext();
		Map<String, Object> beanWithAnnotation = context.getBeansWithAnnotation(ClientParameter.class);
		for (Object obj : beanWithAnnotation.values()) {
			ClientType algorithmType = obj.getClass().getAnnotation(ClientParameter.class).value();
			mappers.put(algorithmType, (ClientParameterMapper<?>) obj);
		}
	}

	@Override
	public <T extends ClientParametersDto> ClientParameterMapper<T> getMapper(ClientType type) {

		var mapper = mappers.get(type);
		return Objects.nonNull(mapper) ? (ClientParameterMapper<T>) mapper : null;
	}
}
