package by.lawaksoft.tradebot.controller.market;

import by.lawaksoft.tradebot.controller.Navigation;
import by.lawaksoft.tradebot.dto.model.CandlestickDto;
import by.lawaksoft.tradebot.dto.model.TickerDto;
import by.lawaksoft.tradebot.dto.request.CandlesticksFilterDto;
import by.lawaksoft.tradebot.service.market.MarketService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Navigation.MARKET)
public class MarketController {

    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping(Navigation.TICKERS)
    public ResponseEntity<Optional<TickerDto>> getTickers(String instrumentId) {

        var tickers = marketService.getTickers(instrumentId);
        return ResponseEntity.ok(tickers);
    }

    @GetMapping(value = Navigation.CANDLES, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<List<CandlestickDto>> getCandlesticks(CandlesticksFilterDto filterDto) {

        var candlesticks = marketService.getCandlesticks(filterDto);
        return ResponseEntity.ok(candlesticks);
    }
}
