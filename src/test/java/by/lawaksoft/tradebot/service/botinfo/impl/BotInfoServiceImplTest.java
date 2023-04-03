package by.lawaksoft.tradebot.service.botinfo.impl;

import by.lawaksoft.tradebot.dto.botinfo.BotParametersDto;
import by.lawaksoft.tradebot.entity.AlgoInstance;
import by.lawaksoft.tradebot.entity.AlgoParam;
import by.lawaksoft.tradebot.entity.AlgoSetting;
import by.lawaksoft.tradebot.entity.AlgoType;
import by.lawaksoft.tradebot.entity.Instrument;
import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.entity.enums.AlgorithmType;
import by.lawaksoft.tradebot.repository.AlgoInstanceRepository;
import by.lawaksoft.tradebot.repository.AlgoParamRepository;
import by.lawaksoft.tradebot.repository.AlgoSettingRepository;
import by.lawaksoft.tradebot.repository.InstrumentRepository;
import by.lawaksoft.tradebot.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BotInfoServiceImplTest {

	@Autowired
	private BotInfoServiceImpl botInfoService;
	@MockBean
	private SecurityContextHolder securityContextHolder;
	@MockBean
	private AlgoInstanceRepository algoInstanceRepository;
	@MockBean
	private AlgoSettingRepository algoSettingRepository;
	@MockBean
	private AlgoParamRepository algoParamRepository;
	@MockBean
	private InstrumentRepository instrumentRepository;
	@MockBean
	private UserRepository userRepository;
	private static MockedStatic<SecurityContextHolder> securityContextHolderMockedStatic;

	@BeforeAll
	static void beforeAll() {

		securityContextHolderMockedStatic = mockStatic(SecurityContextHolder.class);
	}

	@AfterAll
	static void afterAll() {

		securityContextHolderMockedStatic.close();
	}

	@Test
	void inputParametersSuccess() {

		Map<String, String> mapAlgoParameters = getMapAlgoParameters();
		AlgoSetting mockAlgoSetting = getAlgoSetting();
		User mockUser = getMockUser();
		Instrument mockInstrument = getMockInstrument();

		when(algoSettingRepository.findAlgoSettingByNameSetting(anyString())).thenReturn(Optional.of(mockAlgoSetting));

		mockSecurityContextHolder();

		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));
		when(instrumentRepository.findByInstrumentId(anyString())).thenReturn(Optional.of(mockInstrument));
		when(algoInstanceRepository.save(any())).thenReturn(mock(AlgoInstance.class));
		when(algoParamRepository.saveAll(anyCollection())).thenReturn(List.of(mock(AlgoParam.class)));

		BotParametersDto botParametersDto = botInfoService.inputParameters(mapAlgoParameters);

		assertEquals(mapAlgoParameters.get("tradeMarketPare"), botParametersDto.getTradeMarketPare());

		verify(algoSettingRepository, times(6)).findAlgoSettingByNameSetting(anyString());
		verify(instrumentRepository, times(1)).findByInstrumentId(anyString());
		verify(userRepository, times(1)).findByUsername(anyString());
		verify(algoInstanceRepository, times(1)).save(any());
		verify(algoParamRepository, times(1)).saveAll(anyCollection());
	}

	@Test
	void inputParametersInstrumentNotFound() {

		Map<String, String> mapAlgoParameters = getMapAlgoParameters();
		AlgoSetting mockAlgoSetting = getAlgoSetting();
		User mockUser = getMockUser();

		when(algoSettingRepository.findAlgoSettingByNameSetting(anyString())).thenReturn(Optional.of(mockAlgoSetting));
		mockSecurityContextHolder();
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockUser));
		when(instrumentRepository.findByInstrumentId(anyString())).thenThrow(EntityNotFoundException.class);

		assertThrows(EntityNotFoundException.class, () -> botInfoService.inputParameters(mapAlgoParameters));

		verify(algoSettingRepository, times(1)).findAlgoSettingByNameSetting(anyString());
		verify(instrumentRepository, times(1)).findByInstrumentId(anyString());
		verify(userRepository, times(1)).findByUsername(anyString());
	}

	@Test
	void inputParametersUserNotFound() {

		Map<String, String> mapAlgoParameters = getMapAlgoParameters();
		AlgoSetting mockAlgoSetting = getAlgoSetting();

		when(algoSettingRepository.findAlgoSettingByNameSetting(anyString())).thenReturn(Optional.of(mockAlgoSetting));
		mockSecurityContextHolder();
		when(userRepository.findByUsername(anyString())).thenThrow(EntityNotFoundException.class);

		assertThrows(EntityNotFoundException.class, () -> botInfoService.inputParameters(mapAlgoParameters));

		verify(algoSettingRepository, times(1)).findAlgoSettingByNameSetting(anyString());
		verify(userRepository, times(1)).findByUsername(anyString());
	}

	@Test
	void inputParametersAlgoSettingNotFound() {

		Map<String, String> mapAlgoParameters = getMapAlgoParameters();

		when(algoSettingRepository.findAlgoSettingByNameSetting(anyString())).thenThrow(EntityNotFoundException.class);

		assertThrows(EntityNotFoundException.class, () -> botInfoService.inputParameters(mapAlgoParameters));

		verify(algoSettingRepository, times(1)).findAlgoSettingByNameSetting(anyString());
	}

	private void mockSecurityContextHolder() {

		SecurityContext mockSecurityContext = mock(SecurityContext.class);
		securityContextHolderMockedStatic.when(SecurityContextHolder::getContext).thenReturn(mockSecurityContext);
		Authentication authentication = mock(Authentication.class);
		when(mockSecurityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn("username");
	}

	private Instrument getMockInstrument() {

		return Instrument.builder()
				.id(1L)
				.instrumentId("BTC-USD-SWAP")
				.build();
	}

	private User getMockUser() {

		return User.builder()
				.id(1L)
				.username("user")
				.build();
	}

	private AlgoSetting getAlgoSetting() {

		return AlgoSetting.builder()
				.id(1L)
				.nameSetting("nameSetting")
				.algoType(AlgoType.builder()
						.id(1L)
						.name(AlgorithmType.SIMPLE.text())
						.build())
				.build();
	}

	private Map<String, String> getMapAlgoParameters() {

		return Map.of("accountBalance", "100",
				"upperGridRange", "40",
				"lowerGridRange", "20",
				"stepsCounts", "2",
				"tradeMarketPare", "BTC-USD-SWAP"
				);
	}
}