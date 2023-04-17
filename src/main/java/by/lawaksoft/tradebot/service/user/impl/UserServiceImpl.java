package by.lawaksoft.tradebot.service.user.impl;

import by.lawaksoft.tradebot.dto.user.UserRegistrationDto;
import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.exception.dto.BusinessException;
import by.lawaksoft.tradebot.exception.dto.enums.ERROR_MESSAGE;
import by.lawaksoft.tradebot.repository.UserRepository;
import by.lawaksoft.tradebot.service.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	@Override
	public boolean registration(UserRegistrationDto userRegistrationDto) {

		boolean isValid = validateRegistrationData(userRegistrationDto);
		if (!isValid) {
			throw new BusinessException(ERROR_MESSAGE.REGISTRATION_FAILED.getMessage());
		}
		User user = createUser(userRegistrationDto);
		userRepository.save(user);
		return true;
	}

	private User createUser(UserRegistrationDto userRegistrationDto) {

		return User.builder()
				.username(userRegistrationDto.getUsername())
				.email(userRegistrationDto.getEmail())
				.password(new BCryptPasswordEncoder().encode(userRegistrationDto.getPassword()))
				.build();
	}

	private boolean validateRegistrationData(UserRegistrationDto userRegistrationDto) {

		Boolean existsByEmail = userRepository.existsByEmail(userRegistrationDto.getEmail());
		Boolean existsByUsername = userRepository.existsByUsername(userRegistrationDto.getUsername());
		return !existsByUsername && !existsByEmail;
	}
}
