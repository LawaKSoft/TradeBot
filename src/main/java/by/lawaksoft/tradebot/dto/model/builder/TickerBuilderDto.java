package by.lawaksoft.tradebot.dto.model.builder;

import by.lawaksoft.tradebot.dto.model.TickerDto;

public class TickerBuilderDto {

	private String instrumentType;
	private String instrumentId;
	private double lastPrice;
	private double lastSize;
	private double askPrice;
	private double askSize;
	private double bestPrice;
	private double bestSize;
	private double open24hPrice;
	private double highest24hPrice;
	private double lowest24h;
	private double volume24hCurrency;
	private double volume24hContract;
	private double priceUTC0;
	private double priceUTC8;
	private long timestamp;

	private TickerBuilderDto() {

	}

	public static TickerBuilderDto newBuilder() {

		return new TickerBuilderDto();
	}

	public TickerDto build() {

		return new TickerDto(instrumentType, instrumentId, lastPrice, lastSize, askPrice, askSize, bestPrice, bestSize, open24hPrice, highest24hPrice, lowest24h, volume24hCurrency,
				volume24hContract, priceUTC0, priceUTC8, timestamp);
	}

	public TickerBuilderDto instrumentType(String instrumentType) {

		this.instrumentType = instrumentType;
		return this;
	}

	public TickerBuilderDto instrumentId(String instrumentId) {

		this.instrumentId = instrumentId;
		return this;
	}

	public TickerBuilderDto lastPrice(double lastPrice) {

		this.lastPrice = lastPrice;
		return this;
	}

	public TickerBuilderDto lastSize(double lastSize) {

		this.lastSize = lastSize;
		return this;
	}

	public TickerBuilderDto askPrice(double askPrice) {

		this.askPrice = askPrice;
		return this;
	}

	public TickerBuilderDto askSize(double askSize) {

		this.askSize = askSize;
		return this;
	}

	public TickerBuilderDto bestPrice(double bestPrice) {

		this.bestPrice = bestPrice;
		return this;
	}

	public TickerBuilderDto bestSize(double bestSize) {

		this.bestSize = bestSize;
		return this;
	}

	public TickerBuilderDto open24hPrice(double open24hPrice) {

		this.open24hPrice = open24hPrice;
		return this;
	}

	public TickerBuilderDto highest24hPrice(double highest24hPrice) {

		this.highest24hPrice = highest24hPrice;
		return this;
	}

	public TickerBuilderDto lowest24h(double lowest24h) {

		this.lowest24h = lowest24h;
		return this;
	}

	public TickerBuilderDto volume24hCurrency(double volume24hCurrency) {

		this.volume24hCurrency = volume24hCurrency;
		return this;
	}

	public TickerBuilderDto volume24hContract(double volume24hContract) {

		this.volume24hContract = volume24hContract;
		return this;
	}

	public TickerBuilderDto priceUTC0(double priceUTC0) {

		this.priceUTC0 = priceUTC0;
		return this;
	}

	public TickerBuilderDto priceUTC8(double priceUTC8) {

		this.priceUTC8 = priceUTC8;
		return this;
	}

	public TickerBuilderDto timestamp(long timestamp) {

		this.timestamp = timestamp;
		return this;
	}
}
