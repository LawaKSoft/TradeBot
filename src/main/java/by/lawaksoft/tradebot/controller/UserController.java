package by.lawaksoft.tradebot.controller;

import by.lawaksoft.tradebot.dto.user.UserRegistrationDto;
import by.lawaksoft.tradebot.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {

		this.userService = userService;
	}

	@PostMapping("/registration")
	public ResponseEntity<Boolean> userRegistration(@RequestBody UserRegistrationDto userRegistrationDto) {

		boolean registration;
		registration = userService.registration(userRegistrationDto);
		return ResponseEntity.ok(registration);
	}
}
