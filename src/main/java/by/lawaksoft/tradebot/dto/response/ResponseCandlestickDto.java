package by.lawaksoft.tradebot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseCandlestickDto extends ResponseDto{

	private final List<String> candlesticks;

	public ResponseCandlestickDto(@JsonProperty("code") String code, @JsonProperty("msg") String message, @JsonProperty("data") List<String> candlesticks) {

		super(code, message);
		this.candlesticks = candlesticks;
	}

	public List<String> getCandlesticks() {

		return candlesticks;
	}
}
