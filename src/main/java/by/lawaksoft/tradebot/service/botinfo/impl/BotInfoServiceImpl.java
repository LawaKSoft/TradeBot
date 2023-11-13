package by.lawaksoft.tradebot.service.botinfo.impl;

import by.lawaksoft.tradebot.beanlocator.impl.AlgoParameterMapperBeanLocatorImpl;
import by.lawaksoft.tradebot.dto.botinfo.BotParametersDto;
import by.lawaksoft.tradebot.entity.AlgoInstance;
import by.lawaksoft.tradebot.entity.AlgoParam;
import by.lawaksoft.tradebot.entity.AlgoSetting;
import by.lawaksoft.tradebot.entity.Instrument;
import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.entity.enums.AlgorithmType;
import by.lawaksoft.tradebot.mapper.parameter.algo.AlgoParameterMapper;
import by.lawaksoft.tradebot.repository.AlgoInstanceRepository;
import by.lawaksoft.tradebot.repository.AlgoParamRepository;
import by.lawaksoft.tradebot.repository.AlgoSettingRepository;
import by.lawaksoft.tradebot.repository.InstrumentRepository;
import by.lawaksoft.tradebot.repository.UserRepository;
import by.lawaksoft.tradebot.service.botinfo.BotInfoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BotInfoServiceImpl implements BotInfoService {

	private final AlgoInstanceRepository algoInstanceRepository;
	private final AlgoSettingRepository algoSettingRepository;
	private final AlgoParamRepository algoParamRepository;
	private final InstrumentRepository instrumentRepository;
	private final UserRepository userRepository;
	private final AlgoParameterMapperBeanLocatorImpl mapperParameterBeanLocator;

	public BotInfoServiceImpl(AlgoInstanceRepository algoInstanceRepository, AlgoSettingRepository algoSettingRepository,
			AlgoParamRepository algoParamRepository, InstrumentRepository instrumentRepository, UserRepository userRepository,
			AlgoParameterMapperBeanLocatorImpl mapperParameterBeanLocator) {

		this.algoInstanceRepository = algoInstanceRepository;
		this.algoSettingRepository = algoSettingRepository;
		this.algoParamRepository = algoParamRepository;
		this.instrumentRepository = instrumentRepository;
		this.userRepository = userRepository;
		this.mapperParameterBeanLocator = mapperParameterBeanLocator;
	}

	@Override
	public BotParametersDto inputParameters(Map<String, String> botParameters) {

		AlgoSetting algoSetting = algoSettingRepository.findAlgoSettingByNameSetting(botParameters.entrySet().iterator().next().getKey()).orElseThrow(EntityNotFoundException::new);
		String algoName = algoSetting.getAlgoType().getName();
		AlgoParameterMapper<BotParametersDto> mapper = mapperParameterBeanLocator.getMapper(AlgorithmType.byText(algoName));
		BotParametersDto botParametersDto = mapper.toParametersDto(botParameters);

		AlgoInstance algoInstance = createAlgoInstance(botParametersDto.getTradeMarketPare());
		algoInstance = algoInstanceRepository.save(algoInstance);
		saveParameters(botParameters, algoInstance);
		return botParametersDto;
	}

	private void saveParameters(Map<String, String> botParameters, AlgoInstance algoInstance) {

		var algoParam = botParameters.entrySet().stream().map(entry -> {
			AlgoSetting algoSetting = algoSettingRepository.findAlgoSettingByNameSetting(entry.getKey()).orElseThrow(EntityNotFoundException::new);
			return AlgoParam.builder()
					.algoInstance(algoInstance)
					.value(entry.getValue())
					.algoSetting(algoSetting)
					.build();
		}).toList();
		algoParamRepository.saveAll(algoParam);
	}

	private AlgoInstance createAlgoInstance(String tradeMarketPare) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
		Instrument instrument = instrumentRepository.findByName(tradeMarketPare).orElseThrow(EntityNotFoundException::new);
		return AlgoInstance.builder()
				.user(user)
				.instrument(instrument)
				.build();
	}
}
