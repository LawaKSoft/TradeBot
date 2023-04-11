package by.lawaksoft.tradebot.beanlocator;

import by.lawaksoft.tradebot.dto.botinfo.BotParametersDto;
import by.lawaksoft.tradebot.entity.enums.AlgorithmBot;
import by.lawaksoft.tradebot.mapper.parameter.ParameterMapper;
import org.springframework.stereotype.Component;

@Component
public interface MapperAlgorithmBeanLocator {

    <T extends BotParametersDto> ParameterMapper<T> getMapper(AlgorithmBot type);
}
