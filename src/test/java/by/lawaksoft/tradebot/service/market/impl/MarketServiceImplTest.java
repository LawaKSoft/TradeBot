package by.lawaksoft.tradebot.service.market.impl;

import by.lawaksoft.tradebot.client.MarketClient;
import by.lawaksoft.tradebot.dto.model.market.CandlestickDto;
import by.lawaksoft.tradebot.dto.model.market.TickerDto;
import by.lawaksoft.tradebot.dto.request.CandlesticksFilterDto;
import by.lawaksoft.tradebot.dto.response.candlestick.ResponseCandlestickDto;
import by.lawaksoft.tradebot.dto.response.ticker.FullTickerDto;
import by.lawaksoft.tradebot.dto.response.ticker.ResponseTickerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MarketServiceImplTest {

	@InjectMocks
	private MarketServiceImpl service;

	@Mock
	private MarketClient marketClient;

	@Test
	void getTickers() {

		ResponseTickerDto mockResponseTickerDto = getMockResponseTickerDto();

		when(marketClient.getTickers(anyString())).thenReturn(mockResponseTickerDto);

		TickerDto ticker = service.getTickers(anyString());

		assertEquals("SWAP", ticker.getInstrumentType());
		assertEquals("BTC-USD-SWAP", ticker.getInstrumentId());
		verify(marketClient, times(1)).getTickers(anyString());
	}

	@Test
	void getCandlesticks() {

		ResponseCandlestickDto mockResponseCandlestickDto = getMockResponseCandlestickDto();
		CandlesticksFilterDto mockCandlesticksFilterDto = getMockCandlesticksFilterDto();

		when(marketClient.getCandlesticks(anyString(), any(), any(), any(), any())).thenReturn(mockResponseCandlestickDto);

		List<CandlestickDto> candlesticks = service.getCandlesticks(mockCandlesticksFilterDto);

		assertEquals(candlesticks.size(), Integer.parseInt(mockCandlesticksFilterDto.getLimit()));
		verify(marketClient, times(1)).getCandlesticks(anyString(), any(), any(), any(), any());
	}

	private ResponseCandlestickDto getMockResponseCandlestickDto() {

		return ResponseCandlestickDto.builder()
				.code("0")
				.message("")
				.candlesticks(List.of(
						new String[] {"1675857720000", "23169.3", "23169.3", "23169.3", "23169.3", "0", "0", "0", "0"},
						new String[] {"1675857660000", "23169.3", "23169.3", "23169.3", "23169.3", "183", "0.7899", "18300", "1"},
						new String[] {"1675857600000", "23163.4", "23166.5", "23163.4", "23166.5", "213", "0.9196", "21300", "1"},
						new String[] {"1675857540000", "23163.4", "23163.5", "23163.4", "23163.4", "83", "0.3583", "8300", "1"},
						new String[] {"1675857480000", "23155.8", "23160.9", "23155.8", "23160.9", "545", "2.3536", "54500", "1"}))
				.build();
	}

	private CandlesticksFilterDto getMockCandlesticksFilterDto() {

		return CandlesticksFilterDto.builder()
				.instId("BTC-USD-SWAP")
				.bar(null)
				.after(null)
				.before(null)
				.limit("5")
				.build();
	}

	private ResponseTickerDto getMockResponseTickerDto() {

		return ResponseTickerDto.builder()
				.code("0")
				.message("")
				.tickers(List.of(FullTickerDto.builder()
						.instrumentType("SWAP")
						.instrumentId("BTC-USD-SWAP")
						.lastPrice("23165.4")
						.lastSize("29")
						.askPrice("23165.4")
						.askSize("1101")
						.bestPrice("23165.3")
						.bestSize("626")
						.open24hPrice("22989")
						.highest24hPrice("23468")
						.lowest24h("22768.1")
						.volume24hCurrency("15039.4845")
						.volume24hContract("3476159")
						.timestamp("1675853598335")
						.priceUTC0("23250.1")
						.priceUTC8("22909.6")
						.build()))
				.build();
	}
}