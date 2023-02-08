package by.lawaksoft.tradebot.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("by.lawaksoft.tradebot.client")
@ComponentScan("by.lawaksoft.tradebot")
public class AppConfiguration {
}
