package by.lawaksoft.tradebot.service.market.impl;

import by.lawaksoft.tradebot.client.MarketClient;
import by.lawaksoft.tradebot.dto.model.CandlestickDto;
import by.lawaksoft.tradebot.dto.model.TickerDto;
import by.lawaksoft.tradebot.dto.request.CandlesticksFilterDto;
import by.lawaksoft.tradebot.dto.response.ResponseCandlestickDto;
import by.lawaksoft.tradebot.dto.response.ResponseTickerDto;
import by.lawaksoft.tradebot.mapper.DtoMapper;
import by.lawaksoft.tradebot.service.market.MarketService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MarketServiceImpl implements MarketService {

    private final MarketClient marketClient;

    public MarketServiceImpl(MarketClient marketClient) {

        this.marketClient = marketClient;
    }

    public Optional<TickerDto> getTickers(String filterDto) {

        ResponseTickerDto responseTickerDto = marketClient.getTickers(filterDto);
        return responseTickerDto.getTickers().stream().map(DtoMapper::toTickerDto).findFirst();
    }

    public List<CandlestickDto> getCandlesticks(CandlesticksFilterDto filterDto) {

        ResponseCandlestickDto responseCandlestickDto = marketClient.getCandlesticks(filterDto);
        return responseCandlestickDto.getCandlesticks().stream().map(DtoMapper::toCandlestickDto).collect(Collectors.toList());
    }
}
