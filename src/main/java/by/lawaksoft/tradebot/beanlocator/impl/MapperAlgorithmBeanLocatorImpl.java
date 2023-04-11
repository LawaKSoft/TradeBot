package by.lawaksoft.tradebot.beanlocator.impl;

import by.lawaksoft.tradebot.beanlocator.MapperAlgorithmBeanLocator;
import by.lawaksoft.tradebot.dto.botinfo.BotParametersDto;
import by.lawaksoft.tradebot.entity.enums.AlgorithmBot;
import by.lawaksoft.tradebot.entity.enums.AlgorithmType;
import by.lawaksoft.tradebot.mapper.manager.Algorithm;
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
public class MapperAlgorithmBeanLocatorImpl implements MapperAlgorithmBeanLocator {

    private final Map<AlgorithmBot, ParameterMapper<?>> mappers = new HashMap<>();

    @EventListener
    public void mapperBeanListener(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        Map<String, Object> beanWithAnnotation = context.getBeansWithAnnotation(Algorithm.class);
        for (Object obj : beanWithAnnotation.values()) {
            AlgorithmBot algorithmBot = obj.getClass().getAnnotation(Algorithm.class).value();
            mappers.put(algorithmBot, (ParameterMapper<?>) obj);
        }
    }

    @Override
    public <T extends BotParametersDto> ParameterMapper<T> getMapper(AlgorithmBot type) {

        var mapper = mappers.get(type);
        return Objects.nonNull(mapper) ? (ParameterMapper<T>) mapper : null;
    }
}
