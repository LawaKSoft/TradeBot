package by.lawaksoft.tradebot.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableFeignClients("by.lawaksoft.tradebot.client")
@ComponentScan("by.lawaksoft.tradebot")
@EnableAsync
@EnableScheduling
public class AppConfiguration {
}
