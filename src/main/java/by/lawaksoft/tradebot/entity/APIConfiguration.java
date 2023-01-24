package by.lawaksoft.tradebot.entity;

import lombok.*;

import org.springframework.stereotype.Component;

@Getter
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIConfiguration {

    private String apiKey;
    private String secretKey;
    private String passphrase;
}
