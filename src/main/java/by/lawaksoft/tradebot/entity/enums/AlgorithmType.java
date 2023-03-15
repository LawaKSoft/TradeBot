package by.lawaksoft.tradebot.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AlgorithmType {

	SIMPLE("simple");

	private final String text;

	public String text() {

		return this.text;
	}

	public static AlgorithmType byText(String text) {

		return AlgorithmType.valueOf(text);
	}
}
