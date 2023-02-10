package by.lawaksoft.tradebot.dto.response.ticker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ResponseTickerDto {

	@JsonProperty("code")
	private final String code;
	@JsonProperty("msg")
	private final String message;
	@JsonProperty("data")
	private final List<FullTickerDto> tickers;
}
