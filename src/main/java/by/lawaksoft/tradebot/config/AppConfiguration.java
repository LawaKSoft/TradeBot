package by.lawaksoft.tradebot.config;

import by.lawaksoft.tradebot.entity.APIConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public APIConfiguration apiConfiguration(@Value("${API_KEY}")    String apiKey,
                                             @Value("${SECRET_KEY}") String secretKey,
                                             @Value("${PASSPHRASE}") String passphrase) {
        return APIConfiguration.builder()
                            .apiKey(apiKey)
                            .secretKey(secretKey)
                            .passphrase(passphrase)
                            .build();
    }
}
