package by.lawaksoft.tradebot.mapper.parameter.client;

import by.lawaksoft.tradebot.entity.enums.ClientType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClientParameter {

	ClientType value();
}
