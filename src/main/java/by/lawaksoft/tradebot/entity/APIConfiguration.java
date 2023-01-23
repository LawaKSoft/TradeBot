package by.lawaksoft.tradebot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Component
public class APIConfiguration {

    @Value("${API_KEY}")
    private String apiKey;

    @Value("${SECRET_KEY}")
    private String secretKey;

    @Value("${PASSPHRASE}")
    private String passphrase;
}
