package by.lawaksoft.tradebot.entity.enums;

import lombok.AllArgsConstructor;

import java.util.Locale;

@AllArgsConstructor
public enum ClientType {

	OKX("okx");

	private final String text;

	public String text() {

		return this.text;
	}

	public static ClientType byText(String text) {

		return ClientType.valueOf(text.toUpperCase(Locale.ROOT));
	}
}
