package by.lawaksoft.tradebot.mapper.manager;

import by.lawaksoft.tradebot.entity.enums.AlgorithmBot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Algorithm {

    AlgorithmBot value();
}
