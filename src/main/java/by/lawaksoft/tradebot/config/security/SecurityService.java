package by.lawaksoft.tradebot.config.security;

import by.lawaksoft.tradebot.entity.User;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    public User getUser() {
        return new User();
    }
}
