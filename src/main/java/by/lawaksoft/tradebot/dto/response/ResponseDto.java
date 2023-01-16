package by.lawaksoft.tradebot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDto {

	private final String code;
	private final String message;

	public ResponseDto(@JsonProperty("code") String code, @JsonProperty("msg") String message) {

		this.code = code;
		this.message = message;
	}

	public String getCode() {

		return code;
	}

	public String getMessage() {

		return message;
	}
}
