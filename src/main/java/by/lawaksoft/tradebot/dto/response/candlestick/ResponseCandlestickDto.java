package by.lawaksoft.tradebot.dto.response.candlestick;

import by.lawaksoft.tradebot.dto.response.ResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseCandlestickDto extends ResponseDto {

	private final List<String[]> candlesticks;

	public ResponseCandlestickDto(@JsonProperty("code") String code, @JsonProperty("msg") String message, @JsonProperty("data") List<String[]> candlesticks) {

		super(code, message);
		this.candlesticks = candlesticks;
	}
}
