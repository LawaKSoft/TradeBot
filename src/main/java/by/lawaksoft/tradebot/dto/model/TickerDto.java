package by.lawaksoft.tradebot.dto.model;

public class TickerDto {

	private final String instrumentType;
	private final String instrumentId;
	private final double lastPrice;
	private final double lastSize;
	private final double askPrice;
	private final double askSize;
	private final double bestPrice;
	private final double bestSize;
	private final double open24hPrice;
	private final double highest24hPrice;
	private final double lowest24h;
	private final double volume24hCurrency;
	private final double volume24hContract;
	private final double priceUTC0;
	private final double priceUTC8;
	private final long timestamp;

	public TickerDto(String instrumentType, String instrumentId, double lastPrice, double lastSize, double askPrice, double askSize, double bestPrice, double bestSize,
			double open24hPrice, double highest24hPrice, double lowest24h, double volume24hCurrency, double volume24hContract, double priceUTC0, double priceUTC8, long timestamp) {

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

	public double getLastPrice() {

		return lastPrice;
	}

	public double getLastSize() {

		return lastSize;
	}

	public double getAskPrice() {

		return askPrice;
	}

	public double getAskSize() {

		return askSize;
	}

	public double getBestPrice() {

		return bestPrice;
	}

	public double getBestSize() {

		return bestSize;
	}

	public double getOpen24hPrice() {

		return open24hPrice;
	}

	public double getHighest24hPrice() {

		return highest24hPrice;
	}

	public double getLowest24h() {

		return lowest24h;
	}

	public double getVolume24hCurrency() {

		return volume24hCurrency;
	}

	public double getVolume24hContract() {

		return volume24hContract;
	}

	public double getPriceUTC0() {

		return priceUTC0;
	}

	public double getPriceUTC8() {

		return priceUTC8;
	}

	public long getTimestamp() {

		return timestamp;
	}
}
