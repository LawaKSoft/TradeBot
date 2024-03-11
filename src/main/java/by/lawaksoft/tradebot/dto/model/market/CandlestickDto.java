package by.lawaksoft.tradebot.dto.model.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class CandlestickDto {

	private final long timestamp;
	private final BigDecimal openPrice;
	private final BigDecimal highestPrice;
	private final BigDecimal lowestPrice;
	private final BigDecimal closePrice;
	private final BigDecimal volumeContract;
	private final BigDecimal volCurrency;
	private final BigDecimal volCurrencyQuote;
	private final boolean confirm;
}
