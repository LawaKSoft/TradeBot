package by.lawaksoft.tradebot.service.market.impl;

import by.lawaksoft.tradebot.client.MarketClient;
import by.lawaksoft.tradebot.dto.model.market.CandlestickDto;
import by.lawaksoft.tradebot.dto.model.market.TickerDto;
import by.lawaksoft.tradebot.dto.request.CandlesticksFilterDto;
import by.lawaksoft.tradebot.dto.response.candlestick.ResponseCandlestickDto;
import by.lawaksoft.tradebot.dto.response.ticker.ResponseTickerDto;
import by.lawaksoft.tradebot.mapper.DtoMapper;
import by.lawaksoft.tradebot.service.market.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {

    private final MarketClient marketClient;

    @Autowired
    public MarketServiceImpl(MarketClient marketClient) {

        this.marketClient = marketClient;
    }

    @Override
    public TickerDto getTickers(String filterDto) {

        ResponseTickerDto responseTickerDto = marketClient.getTickers(filterDto);
        return responseTickerDto.getTickers().stream().map(DtoMapper::toTickerDto).findFirst().get();
    }

    @Override
    public List<CandlestickDto> getCandlesticks(CandlesticksFilterDto filterDto) {

        ResponseCandlestickDto responseCandlestickDto = marketClient.getCandlesticks(filterDto.getInstId(), filterDto.getBar(), filterDto.getAfter(), filterDto.getBefore(),
                filterDto.getLimit());
        return responseCandlestickDto.getCandlesticks().stream().map(DtoMapper::toCandlestickDto).toList();
    }
}
