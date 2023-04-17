package by.lawaksoft.tradebot.service.clientinfo.impl;

import by.lawaksoft.tradebot.dto.clientinfo.ClientParametersDto;
import by.lawaksoft.tradebot.dto.clientinfo.OkxClientParameterDto;
import by.lawaksoft.tradebot.entity.AlgoParam;
import by.lawaksoft.tradebot.entity.ClientParam;
import by.lawaksoft.tradebot.entity.ClientSetting;
import by.lawaksoft.tradebot.entity.ClientType;
import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.repository.ClientParamRepository;
import by.lawaksoft.tradebot.repository.ClientSettingRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientInfoServiceImplTest {

	@Autowired
	private ClientInfoServiceImpl service;
	@MockBean
	private UserRepository userRepository;
	@MockBean
	private ClientSettingRepository clientSettingRepository;
	@MockBean
	private ClientParamRepository clientParamRepository;
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
	void inputParameters() {

		Map<String, String> mockParameters = getMockedParameters();
		ClientSetting mockedClientSetting = getMockedClientSetting();
		User mockedUser = getMockedUser();

		when(clientSettingRepository.findClientSettingByNameSetting(anyString())).thenReturn(Optional.of(mockedClientSetting));
		mockSecurityContextHolder();
		when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(mockedUser));
		when(clientParamRepository.saveAll(anyCollection())).thenReturn(List.of(mock(ClientParam.class)));

		ClientParametersDto clientParametersDto = service.inputParameters(mockParameters);

		assertEquals(mockParameters.get("API_KEY"), ((OkxClientParameterDto)clientParametersDto).getOkxApiKey());

		verify(clientSettingRepository, times(4)).findClientSettingByNameSetting(anyString());
		verify(userRepository, times(1)).findByUsername(anyString());
		verify(clientParamRepository, times(1)).saveAll(anyCollection());
	}

	@Test
	void inputParametersUserNotFound() {

		Map<String, String> mockParameters = getMockedParameters();
		ClientSetting mockedClientSetting = getMockedClientSetting();

		when(clientSettingRepository.findClientSettingByNameSetting(anyString())).thenReturn(Optional.of(mockedClientSetting));
		mockSecurityContextHolder();
		when(userRepository.findByUsername(anyString())).thenThrow(EntityNotFoundException.class);

		assertThrows(EntityNotFoundException.class, () -> service.inputParameters(mockParameters));

		verify(clientSettingRepository, times(4)).findClientSettingByNameSetting(anyString());
		verify(userRepository, times(1)).findByUsername(anyString());
	}

	@Test
	void inputParametersClientSettingsNotFound() {

		Map<String, String> mockParameters = getMockedParameters();

		when(clientSettingRepository.findClientSettingByNameSetting(anyString())).thenThrow(EntityNotFoundException.class);

		assertThrows(EntityNotFoundException.class, () -> service.inputParameters(mockParameters));

		verify(clientSettingRepository, times(1)).findClientSettingByNameSetting(anyString());
	}



	private User getMockedUser() {

		return User.builder()
				.id(1L)
				.username("user")
				.build();
	}

	private void mockSecurityContextHolder() {

		SecurityContext mockSecurityContext = mock(SecurityContext.class);
		securityContextHolderMockedStatic.when(SecurityContextHolder::getContext).thenReturn(mockSecurityContext);
		Authentication authentication = mock(Authentication.class);
		when(mockSecurityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn("username");
	}

	private ClientSetting getMockedClientSetting() {

		return ClientSetting.builder()
				.id(1L)
				.nameSetting("API_KEY")
				.clientType(ClientType.builder()
						.id(1L)
						.name("OKX")
						.build())
				.build();
	}

	private Map<String, String> getMockedParameters() {

		return  Map.of("API_KEY", "API_KEY_VALUE",
				"PASSPHRASE", "PASSPHRASE_VALUE",
				"SECRET_KEY", "SECRET_KEY_VALUE"
		);
	}
}