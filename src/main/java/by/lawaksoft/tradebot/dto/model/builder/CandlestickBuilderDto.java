package by.lawaksoft.tradebot.dto.model.builder;

import by.lawaksoft.tradebot.dto.model.CandlestickDto;

public class CandlestickBuilderDto {

	private long timestamp;
	private double openPrice;
	private double highestPrice;
	private double lowestPrice;
	private double closePrice;
	private double volumeContract;
	private double volCurrency;
	private double volCurrencyQuote;
	private boolean confirm;

	private CandlestickBuilderDto() {

	}

	public static CandlestickBuilderDto newBuilder() {

		return new CandlestickBuilderDto();
	}

	public CandlestickDto build() {

		return new CandlestickDto(timestamp, openPrice, highestPrice, lowestPrice, closePrice, volumeContract, volCurrency, volCurrencyQuote, confirm);
	}

	public CandlestickBuilderDto timestamp(long timestamp) {

		this.timestamp = timestamp;
		return this;
	}

	public CandlestickBuilderDto openPrice(double openPrice) {

		this.openPrice = openPrice;
		return this;
	}

	public CandlestickBuilderDto highestPrice(double highestPrice) {

		this.highestPrice = highestPrice;
		return this;
	}

	public CandlestickBuilderDto lowestPrice(double lowestPrice) {

		this.lowestPrice = lowestPrice;
		return this;
	}

	public CandlestickBuilderDto closePrice(double closePrice) {

		this.closePrice = closePrice;
		return this;
	}

	public CandlestickBuilderDto volumeContract(double volumeContract) {

		this.volumeContract = volumeContract;
		return this;
	}

	public CandlestickBuilderDto volCurrency(double volCurrency) {

		this.volCurrency = volCurrency;
		return this;
	}

	public CandlestickBuilderDto volCurrencyQuote(double volCurrencyQuote) {

		this.volCurrencyQuote = volCurrencyQuote;
		return this;
	}

	public CandlestickBuilderDto confirm(boolean confirm) {

		this.confirm = confirm;
		return this;
	}
}
