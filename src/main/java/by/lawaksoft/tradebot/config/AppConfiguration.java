package by.lawaksoft.tradebot.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableFeignClients("by.lawaksoft.tradebot.client")
@EnableMongoRepositories(basePackages = {"by.lawaksoft.tradebot.repository.mongo"})
@ComponentScan("by.lawaksoft.tradebot")
public class AppConfiguration {
}
