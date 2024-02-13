package by.lawaksoft.tradebot.service.price_reaction;

import by.lawaksoft.tradebot.dto.algorithm.AlgoLayerDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface PriceReactionService {

	void onPriceReaction(AlgoLayerDto algoLayerDto, BigDecimal price);
}
