package by.lawaksoft.tradebot.dto.response.ticker;

import by.lawaksoft.tradebot.dto.response.ResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseTickerDto extends ResponseDto {

	private final List<FullTickerDto> tickers;

	public ResponseTickerDto(@JsonProperty("code") String code, @JsonProperty("msg") String message, @JsonProperty("data") List<FullTickerDto> tickers) {

		super(code, message);
		this.tickers = tickers;
	}

	public List<FullTickerDto> getTickers() {

		return tickers;
	}
}
