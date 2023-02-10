package by.lawaksoft.tradebot.service.market;

import by.lawaksoft.tradebot.dto.model.market.CandlestickDto;
import by.lawaksoft.tradebot.dto.model.market.TickerDto;
import by.lawaksoft.tradebot.dto.request.CandlesticksFilterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MarketService {

    TickerDto getTickers(String filterDto);

    List<CandlestickDto> getCandlesticks(CandlesticksFilterDto filterDto);
}
