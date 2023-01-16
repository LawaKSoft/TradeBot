package by.lawaksoft.tradebot.service.market;

import by.lawaksoft.tradebot.dto.model.market.CandlesticksDto;
import by.lawaksoft.tradebot.dto.request.CandlesticksFilterDto;
import by.lawaksoft.tradebot.dto.model.market.TickerDto;
import org.springframework.stereotype.Service;

@Service
public interface MarketService {

    TickerDto getTickers(String filterDto);

    CandlesticksDto getCandlesticks(CandlesticksFilterDto filterDto);
}
