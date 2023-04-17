package by.lawaksoft.tradebot.service.user;

import by.lawaksoft.tradebot.dto.user.UserRegistrationDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	boolean registration(UserRegistrationDto userRegistrationDto);
}
