package by.lawaksoft.tradebot.controller.market;

import by.lawaksoft.tradebot.dto.model.market.CandlestickDto;
import by.lawaksoft.tradebot.dto.model.market.TickerDto;
import by.lawaksoft.tradebot.dto.request.CandlesticksFilterDto;
import by.lawaksoft.tradebot.service.market.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/market")
public class MarketController {

    private final MarketService marketService;

    @Autowired
    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping("/ticker")
    public ResponseEntity<TickerDto> getTicker(@RequestParam("/instId") String instrumentId) {

        var ticker = marketService.getTickers(instrumentId);
        return ResponseEntity.ok(ticker);
    }

    @GetMapping(value = "/candles")
    public ResponseEntity<List<CandlestickDto>> getCandlesticks(@RequestBody CandlesticksFilterDto filterDto) {

        var candlesticks = marketService.getCandlesticks(filterDto);
        return ResponseEntity.ok(candlesticks);
    }
}
