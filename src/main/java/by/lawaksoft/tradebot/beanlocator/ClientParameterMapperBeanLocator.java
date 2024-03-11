package by.lawaksoft.tradebot.beanlocator;

import by.lawaksoft.tradebot.dto.clientinfo.ClientParametersDto;
import by.lawaksoft.tradebot.entity.enums.ClientType;
import by.lawaksoft.tradebot.mapper.parameter.client.ClientParameterMapper;

public interface ClientParameterMapperBeanLocator {

	<T extends ClientParametersDto> ClientParameterMapper<T> getMapper(ClientType type);
}
