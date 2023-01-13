package by.lawaksoft.tradebot.service.impl;

import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.exception.entity.BusinessException;
import by.lawaksoft.tradebot.exception.entity.enums.ERROR_CODE;
import by.lawaksoft.tradebot.repository.UserRepository;
import by.lawaksoft.tradebot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static by.lawaksoft.tradebot.exception.entity.enums.ERROR_CODE.USER_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(String.format("User with id %s not found", id),
                                                                                    USER_NOT_FOUND, NOT_FOUND));
    }
}
