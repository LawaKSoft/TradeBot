package by.lawaksoft.tradebot.service.clientinfo.impl;

import by.lawaksoft.tradebot.beanlocator.impl.ClientParameterMapperBeanLocatorImpl;
import by.lawaksoft.tradebot.dto.clientinfo.ClientParametersDto;
import by.lawaksoft.tradebot.entity.ClientParam;
import by.lawaksoft.tradebot.entity.ClientSetting;
import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.entity.enums.ClientType;
import by.lawaksoft.tradebot.mapper.parameter.client.ClientParameterMapper;
import by.lawaksoft.tradebot.repository.ClientParamRepository;
import by.lawaksoft.tradebot.repository.ClientSettingRepository;
import by.lawaksoft.tradebot.repository.UserRepository;
import by.lawaksoft.tradebot.service.clientinfo.ClientInfoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ClientInfoServiceImpl implements ClientInfoService {

	private final UserRepository userRepository;
	private final ClientSettingRepository clientSettingRepository;
	private final ClientParamRepository clientParamRepository;
	private final ClientParameterMapperBeanLocatorImpl clientParameterMapperBeanLocator;

	public ClientInfoServiceImpl(UserRepository userRepository, ClientSettingRepository clientSettingRepository,
			ClientParamRepository clientParamRepository, ClientParameterMapperBeanLocatorImpl clientParameterMapperBeanLocator) {

		this.userRepository = userRepository;
		this.clientSettingRepository = clientSettingRepository;
		this.clientParamRepository = clientParamRepository;
		this.clientParameterMapperBeanLocator = clientParameterMapperBeanLocator;
	}

	@Override
	public ClientParametersDto inputParameters(Map<String, String> clientParameters) {

		ClientSetting clientSetting = clientSettingRepository.findClientSettingByNameSetting(clientParameters.entrySet().iterator().next().getKey())
				.orElseThrow(EntityNotFoundException::new);
		String clientName = clientSetting.getClientType().getName();
		ClientParameterMapper<ClientParametersDto> mapper = clientParameterMapperBeanLocator.getMapper(ClientType.byText(clientName));
		ClientParametersDto clientParametersDto = mapper.toParametersDto(clientParameters);

		saveParameters(clientParameters);
		return clientParametersDto;
	}

	private void saveParameters(Map<String, String> clientParameters) {

		User user = getUser();
		var clientParams = clientParameters.entrySet().stream().map(entry -> {
			ClientSetting clientSetting = clientSettingRepository.findClientSettingByNameSetting(entry.getKey()).orElseThrow(EntityNotFoundException::new);
			return ClientParam.builder()
					.user(user)
					.clientSetting(clientSetting)
					.value(entry.getValue())
					.build();
		}).toList();
		clientParamRepository.saveAll(clientParams);
	}

	private User getUser() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
	}
}
