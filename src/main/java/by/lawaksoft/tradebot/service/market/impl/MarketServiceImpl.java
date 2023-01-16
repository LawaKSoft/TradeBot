package by.lawaksoft.tradebot.service.market.impl;

import by.lawaksoft.tradebot.client.MarketClient;
import by.lawaksoft.tradebot.dto.model.market.CandlesticksDto;
import by.lawaksoft.tradebot.dto.request.CandlesticksFilterDto;
import by.lawaksoft.tradebot.dto.model.market.TickerDto;
import by.lawaksoft.tradebot.dto.response.ResponseCandlestickDto;
import by.lawaksoft.tradebot.dto.response.ResponseTickerDto;
import by.lawaksoft.tradebot.service.market.MarketService;

public class MarketServiceImpl implements MarketService {

    private final MarketClient marketClient;

    public MarketServiceImpl(MarketClient marketClient) {

        this.marketClient = marketClient;
    }

    public TickerDto getTickers(String filterDto) {

        ResponseTickerDto tickers = marketClient.getTickers(filterDto);
        return null;
    }

    public CandlesticksDto getCandlesticks(CandlesticksFilterDto filterDto) {

        ResponseCandlestickDto candlesticks = marketClient.getCandlesticks(filterDto);
        return null;
    }
}
