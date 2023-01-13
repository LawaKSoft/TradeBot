package by.lawaksoft.tradebot.config.security.impl;

import by.lawaksoft.tradebot.config.security.SecurityService;
import by.lawaksoft.tradebot.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    //add security
    @Override
    public User getUser() {
        return new User();
    }
}
