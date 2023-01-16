package by.lawaksoft.tradebot.controller.market;

import by.lawaksoft.tradebot.controller.Navigation;
import by.lawaksoft.tradebot.dto.model.market.CandlesticksDto;
import by.lawaksoft.tradebot.dto.request.CandlesticksFilterDto;
import by.lawaksoft.tradebot.dto.model.market.TickerDto;
import by.lawaksoft.tradebot.service.market.MarketService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Navigation.MARKET)
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping(Navigation.TICKERS)
    public ResponseEntity<TickerDto> getTickers(String instrumentId) {

        TickerDto tickers = marketService.getTickers(instrumentId);
        return ResponseEntity.ok(tickers);
    }

    @GetMapping(value = Navigation.CANDLES, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<CandlesticksDto> getCandlesticks(CandlesticksFilterDto filterDto) {

        CandlesticksDto candlesticks = marketService.getCandlesticks(filterDto);
        return ResponseEntity.ok(candlesticks);
    }
}
