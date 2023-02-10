package by.lawaksoft.tradebot.client;

import by.lawaksoft.tradebot.dto.response.candlestick.ResponseCandlestickDto;
import by.lawaksoft.tradebot.dto.response.ticker.ResponseTickerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${MARKET}", url = "${MARKET_URL}")
public interface MarketClient {

	@GetMapping(value = "${TICKER}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	ResponseTickerDto getTickers(@RequestParam("instId") String instId);

	@GetMapping(value = "${CANDLES}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	ResponseCandlestickDto getCandlesticks(@RequestParam("instId") String instId,
			@RequestParam(value = "bar", required = false) String bar,
			@RequestParam(value = "after", required = false) String after,
			@RequestParam(value = "before", required = false) String before,
			@RequestParam(value = "limit", required = false) String limit);
}