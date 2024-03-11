package by.lawaksoft.tradebot.mapper.parameter.algo;

import by.lawaksoft.tradebot.entity.enums.AlgorithmType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AlgoParameter {

	AlgorithmType value();
}
