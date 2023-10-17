package by.lawaksoft.tradebot.exception;

import by.lawaksoft.tradebot.exception.dto.enums.ERROR_MESSAGE;

public class JsonMapperException extends RuntimeException {

	public JsonMapperException(String message) {

		super(ERROR_MESSAGE.JSON_MAPPER_FAILED.getMessage() + message);
	}
}
