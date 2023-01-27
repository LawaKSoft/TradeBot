package by.lawaksoft.tradebot.client;

import by.lawaksoft.tradebot.dto.request.CandlesticksFilterDto;
import by.lawaksoft.tradebot.dto.response.ResponseCandlestickDto;
import by.lawaksoft.tradebot.dto.response.ResponseTickerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ClientNavigation.MARKET, url = ClientNavigation.MARKET_URL)
public interface MarketClient {

	@GetMapping(value = ClientNavigation.TICKER, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	ResponseTickerDto getTickers(@RequestParam(ClientNavigation.INSTRUMENT_ID) String instId);

	@GetMapping(value = ClientNavigation.CANDLES, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	ResponseCandlestickDto getCandlesticks(@RequestParam(ClientNavigation.INSTRUMENT_ID) String instId,
			@RequestParam(value = ClientNavigation.BAR, required = false) String bar,
			@RequestParam(value = ClientNavigation.AFTER, required = false) String after,
			@RequestParam(value = ClientNavigation.BEFORE, required = false) String before,
			@RequestParam(value = ClientNavigation.LIMIT, required = false) String limit);
}
