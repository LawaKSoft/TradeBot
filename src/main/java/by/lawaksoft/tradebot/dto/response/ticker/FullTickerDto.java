package by.lawaksoft.tradebot.dto.response.ticker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FullTickerDto {

	@JsonProperty("instType")
	private final String instrumentType;
	@JsonProperty("instId")
	private final String instrumentId;
	@JsonProperty("last")
	private final String lastPrice;
	@JsonProperty("lastSz")
	private final String lastSize;
	@JsonProperty("askPx")
	private final String askPrice;
	@JsonProperty("askSz")
	private final String askSize;
	@JsonProperty("bidPx")
	private final String bestPrice;
	@JsonProperty("bidSz")
	private final String bestSize;
	@JsonProperty("open24h")
	private final String open24hPrice;
	@JsonProperty("high24h")
	private final String highest24hPrice;
	@JsonProperty("low24h")
	private final String lowest24h;
	@JsonProperty("volCcy24h")
	private final String volume24hCurrency;
	@JsonProperty("vol24h")
	private final String volume24hContract;
	@JsonProperty("sodUtc0")
	private final String priceUTC0;
	@JsonProperty("sodUtc8")
	private final String priceUTC8;
	@JsonProperty("ts")
	private final String timestamp;
}
