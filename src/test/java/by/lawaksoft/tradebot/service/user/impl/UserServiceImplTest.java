package by.lawaksoft.tradebot.service.user.impl;

import by.lawaksoft.tradebot.dto.user.UserRegistrationDto;
import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.exception.dto.BusinessException;
import by.lawaksoft.tradebot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

	@Autowired
	private UserServiceImpl userService;
	@MockBean
	private UserRepository userRepository;

	@Test
	void registration() {

		UserRegistrationDto mockedUserRegistrationDto = getUserRegistrationDto();
		when(userRepository.existsByEmail(anyString())).thenReturn(false);
		when(userRepository.existsByUsername(anyString())).thenReturn(false);
		when(userRepository.save(any())).thenReturn(mock(User.class));

		boolean registration = userService.registration(mockedUserRegistrationDto);

		assertTrue(registration);

		verify(userRepository, times(1)).existsByEmail(anyString());
		verify(userRepository, times(1)).existsByUsername(anyString());
		verify(userRepository, times(1)).save(any());
	}

	@Test
	void registrationEmailExist() {

		UserRegistrationDto mockedUserRegistrationDto = getUserRegistrationDto();
		when(userRepository.existsByEmail(anyString())).thenReturn(true);
		when(userRepository.existsByUsername(anyString())).thenReturn(false);

		assertThrows(BusinessException.class, () -> userService.registration(mockedUserRegistrationDto));

		verify(userRepository, times(1)).existsByEmail(anyString());
		verify(userRepository, times(1)).existsByUsername(anyString());
	}

	@Test
	void registrationUsernameExist() {

		UserRegistrationDto mockedUserRegistrationDto = getUserRegistrationDto();
		when(userRepository.existsByEmail(anyString())).thenReturn(false);
		when(userRepository.existsByUsername(anyString())).thenReturn(true);

		assertThrows(BusinessException.class, () -> userService.registration(mockedUserRegistrationDto));

		verify(userRepository, times(1)).existsByEmail(anyString());
		verify(userRepository, times(1)).existsByUsername(anyString());
	}

	private UserRegistrationDto getUserRegistrationDto() {

		return UserRegistrationDto.builder()
				.username("user")
				.password("password")
				.email("email@email.email")
				.build();
	}
}