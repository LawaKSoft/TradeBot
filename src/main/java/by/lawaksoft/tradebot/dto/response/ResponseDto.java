package by.lawaksoft.tradebot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDto {

	@JsonProperty("code")
	private final String code;
	@JsonProperty("msg")
	private final String message;
}
