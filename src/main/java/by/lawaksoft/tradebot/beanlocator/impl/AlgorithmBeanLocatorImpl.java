package by.lawaksoft.tradebot.beanlocator.impl;

import by.lawaksoft.tradebot.beanlocator.AlgorithmBeanLocator;
import by.lawaksoft.tradebot.entity.enums.AlgorithmBot;
import by.lawaksoft.tradebot.mapper.manager.Algorithm;
import by.lawaksoft.tradebot.service.manager.AlgoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class AlgorithmBeanLocatorImpl implements AlgorithmBeanLocator {

    private final Map<AlgorithmBot, AlgoService> mappers = new HashMap<>();

    @EventListener
    public void mapperBeanListener(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        Map<String, Object> beanWithAnnotation = context.getBeansWithAnnotation(Algorithm.class);
        for (Object obj : beanWithAnnotation.values()) {
            AlgorithmBot algorithmBot = obj.getClass().getAnnotation(Algorithm.class).value();
            mappers.put(algorithmBot, (AlgoService) obj);
        }
    }

    @Override
    public AlgoService getAlgo(AlgorithmBot type) {

        var mapper = mappers.get(type);
        return Objects.nonNull(mapper) ? mapper : null;
    }
}
