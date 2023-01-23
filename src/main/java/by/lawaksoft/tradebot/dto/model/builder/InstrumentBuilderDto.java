package by.lawaksoft.tradebot.dto.model.builder;

import by.lawaksoft.tradebot.dto.model.InstrumentDto;

public class InstrumentBuilderDto {

	private String instrumentType;
	private String instrumentId;
	private String baseCurrency;
	private String quoteCurrency;
	private long listingTime;
	private double tickSize;
	private double lotSize;
	private double minOrderSize;
	private double maxLimitSize;
	private double maxMarketSize;

	private InstrumentBuilderDto() {

	}

	public static InstrumentBuilderDto newBuilder() {

		return new InstrumentBuilderDto();
	}

	public InstrumentDto build() {

		return new InstrumentDto(instrumentType, instrumentId, baseCurrency, quoteCurrency, listingTime, tickSize, lotSize, minOrderSize, maxLimitSize, maxMarketSize);
	}

	public InstrumentBuilderDto instrumentType(String instrumentType) {

		this.instrumentType = instrumentType;
		return this;
	}

	public InstrumentBuilderDto instrumentId(String instrumentId) {

		this.instrumentId = instrumentId;
		return this;
	}

	public InstrumentBuilderDto baseCurrency(String baseCurrency) {

		this.baseCurrency = baseCurrency;
		return this;
	}

	public InstrumentBuilderDto quoteCurrency(String quoteCurrency) {

		this.quoteCurrency = quoteCurrency;
		return this;
	}

	public InstrumentBuilderDto listingTime(long listingTime) {

		this.listingTime = listingTime;
		return this;
	}

	public InstrumentBuilderDto tickSize(double tickSize) {

		this.tickSize = tickSize;
		return this;
	}

	public InstrumentBuilderDto lotSize(double lotSize) {

		this.lotSize = lotSize;
		return this;
	}

	public InstrumentBuilderDto minOrderSize(double minOrderSize) {

		this.minOrderSize = minOrderSize;
		return this;
	}

	public InstrumentBuilderDto maxLimitSize(double maxLimitSize) {

		this.maxLimitSize = maxLimitSize;
		return this;
	}

	public InstrumentBuilderDto maxMarketSize(double maxMarketSize) {

		this.maxMarketSize = maxMarketSize;
		return this;
	}
}
