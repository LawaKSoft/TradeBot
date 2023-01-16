package by.lawaksoft.tradebot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FullTickerDto {

	private final String instrumentType;//instType	String	Instrument type
	private final String instrumentId;//instId	String	Instrument ID
	private final String lastPrice;//last	String	Last traded price
	private final String lastSize;//lastSz	String	Last traded size
	private final String askPrice;//askPx	String	Best ask price
	private final String askSize;//askSz	String	Best ask size
	private final String bestPrice;//bidPx	String	Best bid price
	private final String bestSize;//bidSz	String	Best bid size
	private final String open24hPrice;//open24h	String	Open price in the past 24 hours
	private final String highest24hPrice;//high24h	String	Highest price in the past 24 hours
	private final String lowest24h;//low24h	String	Lowest price in the past 24 hours
	private final String volume24hCurrency;//volCcy24h	String	24h trading volume, with a unit of currency.
	// If it is a derivatives contract, the value is the number of base currency.
	// If it is SPOT/MARGIN, the value is the quantity in quote currency.
	private final String volume24hContract;//vol24h	String	24h trading volume, with a unit of contract.
	// If it is a derivatives contract, the value is the number of contracts.
	// If it is SPOT/MARGIN, the value is the quantity in base currency.
	private final String priceUTC0;//sodUtc0	String	Open price in the UTC 0
	private final String priceUTC8;//sodUtc8	String	Open price in the UTC 8
	private final String timestamp;//ts	String	Ticker data generation time, Unix timestamp format in milliseconds, e.g. 1597026383085

	public FullTickerDto(@JsonProperty("instType") String instrumentType, @JsonProperty("instId") String instrumentId, @JsonProperty("last") String lastPrice,
			@JsonProperty("lastSz") String lastSize, @JsonProperty("askPx") String askPrice, @JsonProperty("askSz") String askSize, @JsonProperty("bidPx") String bestPrice,
			@JsonProperty("bidSz") String bestSize, @JsonProperty("open24h") String open24hPrice, @JsonProperty("high24h") String highest24hPrice,
			@JsonProperty("low24h") String lowest24h, @JsonProperty("volCcy24h") String volume24hCurrency, @JsonProperty("vol24h") String volume24hContract,
			@JsonProperty("sodUtc0") String priceUTC0, @JsonProperty("sodUtc8") String priceUTC8, @JsonProperty("ts") String timestamp) {

		this.instrumentType = instrumentType;
		this.instrumentId = instrumentId;
		this.lastPrice = lastPrice;
		this.lastSize = lastSize;
		this.askPrice = askPrice;
		this.askSize = askSize;
		this.bestPrice = bestPrice;
		this.bestSize = bestSize;
		this.open24hPrice = open24hPrice;
		this.highest24hPrice = highest24hPrice;
		this.lowest24h = lowest24h;
		this.volume24hCurrency = volume24hCurrency;
		this.volume24hContract = volume24hContract;
		this.priceUTC0 = priceUTC0;
		this.priceUTC8 = priceUTC8;
		this.timestamp = timestamp;
	}

	public String getInstrumentType() {

		return instrumentType;
	}

	public String getInstrumentId() {

		return instrumentId;
	}

	public String getLastPrice() {

		return lastPrice;
	}

	public String getLastSize() {

		return lastSize;
	}

	public String getAskPrice() {

		return askPrice;
	}

	public String getAskSize() {

		return askSize;
	}

	public String getBestPrice() {

		return bestPrice;
	}

	public String getBestSize() {

		return bestSize;
	}

	public String getOpen24hPrice() {

		return open24hPrice;
	}

	public String getHighest24hPrice() {

		return highest24hPrice;
	}

	public String getLowest24h() {

		return lowest24h;
	}

	public String getVolume24hCurrency() {

		return volume24hCurrency;
	}

	public String getVolume24hContract() {

		return volume24hContract;
	}

	public String getPriceUTC0() {

		return priceUTC0;
	}

	public String getPriceUTC8() {

		return priceUTC8;
	}

	public String getTimestamp() {

		return timestamp;
	}
}
