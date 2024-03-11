package by.lawaksoft.tradebot.service.storage.impl;

import by.lawaksoft.tradebot.service.storage.PriceStorage;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class OkxPriceStorage implements PriceStorage {

	private final Map<String, BigDecimal> lastInstrumentPrice = new HashMap<>();

	@Override
	public BigDecimal getPriceById(String instId) {

		return lastInstrumentPrice.get(instId);
	}

	@Override
	public void put(String instId, BigDecimal price) {

		lastInstrumentPrice.put(instId, price);
	}
}
