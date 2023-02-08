package by.lawaksoft.tradebot.dto.response.candlestick;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FullCandlestickDto {

	@JsonProperty("ts")
	private final String timestamp;
	@JsonProperty("o")
	private final String openPrice;
	@JsonProperty("h")
	private final String highestPrice;
	@JsonProperty("l")
	private final String lowestPrice;
	@JsonProperty("c")
	private final String closePrice;
	@JsonProperty("vol")
	private final String volumeContract;
	@JsonProperty("volCcy")
	private final String volCurrency;
	@JsonProperty("volCcyQuote")
	private final String volCurrencyQuote;
	@JsonProperty("confirm")
	private final String confirm;
}
