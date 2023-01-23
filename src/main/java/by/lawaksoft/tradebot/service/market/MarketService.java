package by.lawaksoft.tradebot.service.market;

import by.lawaksoft.tradebot.dto.model.CandlestickDto;
import by.lawaksoft.tradebot.dto.model.TickerDto;
import by.lawaksoft.tradebot.dto.request.CandlesticksFilterDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MarketService {

    Optional<TickerDto> getTickers(String filterDto);

    List<CandlestickDto> getCandlesticks(CandlesticksFilterDto filterDto);
}
