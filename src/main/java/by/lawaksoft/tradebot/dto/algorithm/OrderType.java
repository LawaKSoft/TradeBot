package by.lawaksoft.tradebot.dto.algorithm;

public enum OrderType {

	SELL("SELL"),
	BUY("BUY");

	private String text;

	OrderType(String text) {

		this.text = text;
	}

	public String getText() {

		return text;
	}
}
