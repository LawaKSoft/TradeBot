package by.lawaksoft.tradebot.service.market.impl;

import by.lawaksoft.tradebot.dto.model.market.CandlestickDto;
import by.lawaksoft.tradebot.dto.request.CandlesticksFilterDto;
import by.lawaksoft.tradebot.service.market.MarketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MarketServiceImplTest {

	@Autowired
	private MarketService marketService;

	@Test
	void getTickers() {

		var tickers = marketService.getTickers("BTC-USD-SWAP");

		assertNotNull(tickers);
		assertEquals(tickers.getInstrumentId(), "BTC-USD-SWAP");
		assertEquals(tickers.getInstrumentType(), "SWAP");
	}

	@Test
	void getCandlesticks() {

		CandlesticksFilterDto filterDto = new CandlesticksFilterDto("BTC-USD-SWAP", null, null, null, "105");
		List<CandlestickDto> candlesticks = marketService.getCandlesticks(filterDto);

		assertEquals(candlesticks.size(), 105);
	}
}