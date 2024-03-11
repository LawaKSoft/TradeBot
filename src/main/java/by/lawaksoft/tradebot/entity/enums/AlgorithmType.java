package by.lawaksoft.tradebot.entity.enums;

import lombok.AllArgsConstructor;

import java.util.Locale;

@AllArgsConstructor
public enum AlgorithmType {

	SIMPLE("Simple");

	private final String text;

	public String text() {

		return this.text;
	}

	public static AlgorithmType byText(String text) {

		return AlgorithmType.valueOf(text.toUpperCase(Locale.ROOT));
	}
}
