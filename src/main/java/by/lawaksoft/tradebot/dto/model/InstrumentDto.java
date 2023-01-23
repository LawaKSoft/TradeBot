package by.lawaksoft.tradebot.dto.model;

public class InstrumentDto {

	private final String instrumentType;
	private final String instrumentId;
	private final String baseCurrency;
	private final String quoteCurrency;
	private final long listingTime;
	private final double tickSize;
	private final double lotSize;
	private final double minOrderSize;
	private final double maxLimitSize;
	private final double maxMarketSize;

	public InstrumentDto(String instrumentType, String instrumentId, String baseCurrency, String quoteCurrency, long listingTime, double tickSize, double lotSize,
			double minOrderSize, double maxLimitSize, double maxMarketSize) {

		this.instrumentType = instrumentType;
		this.instrumentId = instrumentId;
		this.baseCurrency = baseCurrency;
		this.quoteCurrency = quoteCurrency;
		this.listingTime = listingTime;
		this.tickSize = tickSize;
		this.lotSize = lotSize;
		this.minOrderSize = minOrderSize;
		this.maxLimitSize = maxLimitSize;
		this.maxMarketSize = maxMarketSize;
	}

	public String getInstrumentType() {

		return instrumentType;
	}

	public String getInstrumentId() {

		return instrumentId;
	}

	public String getBaseCurrency() {

		return baseCurrency;
	}

	public String getQuoteCurrency() {

		return quoteCurrency;
	}

	public long getListingTime() {

		return listingTime;
	}

	public double getTickSize() {

		return tickSize;
	}

	public double getLotSize() {

		return lotSize;
	}

	public double getMinOrderSize() {

		return minOrderSize;
	}

	public double getMaxLimitSize() {

		return maxLimitSize;
	}

	public double getMaxMarketSize() {

		return maxMarketSize;
	}
}
