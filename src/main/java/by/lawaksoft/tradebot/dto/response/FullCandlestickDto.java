package by.lawaksoft.tradebot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FullCandlestickDto {

	private final String timestamp;//ts	String	Opening time of the candlestick, Unix timestamp format in milliseconds, e.g. 1597026383085
	private final String openPrice;//o	String	Open price
	private final String highestPrice;//h	String	highest price
	private final String lowestPrice;//l	String	Lowest price
	private final String closePrice;//c	String	Close price
	private final String volumeContract;//vol	String	Trading volume, with a unit of contract.
	// If it is a derivatives contract, the value is the number of contracts.	If it is SPOT/MARGIN, the value is the quantity in base currency.
	private final String volCurrency;//volCcy	String	Trading volume, with a unit of currency.
	// If it is a derivatives contract, the value is the number of base currency.	If it is SPOT/MARGIN, the value is the quantity in quote currency.
	private final String volCurrencyQuote;//volCcyQuote	String	Trading volume, the value is the quantity in quote currency	e.g.
	// The unit is USDT for BTC-USDT and BTC-USDT-SWAP; The unit is USD for BTC-USD-SWAP
	private final String confirm;//confirm	String	The state of candlesticks.0 represents that it is uncompleted, 1 represents that it is completed.

	public FullCandlestickDto(@JsonProperty("ts") String timestamp, @JsonProperty("o") String openPrice, @JsonProperty("h") String highestPrice,
			@JsonProperty("l") String lowestPrice, @JsonProperty("c") String closePrice, @JsonProperty("vol") String volumeContract, @JsonProperty("volCcy") String volCurrency,
			@JsonProperty("volCcyQuote") String volCurrencyQuote, @JsonProperty("confirm") String confirm) {

		this.timestamp = timestamp;
		this.openPrice = openPrice;
		this.highestPrice = highestPrice;
		this.lowestPrice = lowestPrice;
		this.closePrice = closePrice;
		this.volumeContract = volumeContract;
		this.volCurrency = volCurrency;
		this.volCurrencyQuote = volCurrencyQuote;
		this.confirm = confirm;
	}

	public String getTimestamp() {

		return timestamp;
	}

	public String getOpenPrice() {

		return openPrice;
	}

	public String getHighestPrice() {

		return highestPrice;
	}

	public String getLowestPrice() {

		return lowestPrice;
	}

	public String getClosePrice() {

		return closePrice;
	}

	public String getVolumeContract() {

		return volumeContract;
	}

	public String getVolCurrency() {

		return volCurrency;
	}

	public String getVolCurrencyQuote() {

		return volCurrencyQuote;
	}

	public String getConfirm() {

		return confirm;
	}
}
