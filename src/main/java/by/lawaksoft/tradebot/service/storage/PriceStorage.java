package by.lawaksoft.tradebot.service.storage;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface PriceStorage {

	BigDecimal getPriceById(String instId);

	void put(String instId, BigDecimal price);
}
