package by.lawaksoft.tradebot.dto.model;

public class CandlestickDto {

	private final long timestamp;
	private final double openPrice;
	private final double highestPrice;
	private final double lowestPrice;
	private final double closePrice;
	private final double volumeContract;
	private final double volCurrency;
	private final double volCurrencyQuote;
	private final boolean confirm;

	public CandlestickDto(long timestamp, double openPrice, double highestPrice, double lowestPrice, double closePrice, double volumeContract, double volCurrency,
			double volCurrencyQuote, boolean confirm) {

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

	public long getTimestamp() {

		return timestamp;
	}

	public double getOpenPrice() {

		return openPrice;
	}

	public double getHighestPrice() {

		return highestPrice;
	}

	public double getLowestPrice() {

		return lowestPrice;
	}

	public double getClosePrice() {

		return closePrice;
	}

	public double getVolumeContract() {

		return volumeContract;
	}

	public double getVolCurrency() {

		return volCurrency;
	}

	public double getVolCurrencyQuote() {

		return volCurrencyQuote;
	}

	public boolean isConfirm() {

		return confirm;
	}
}
