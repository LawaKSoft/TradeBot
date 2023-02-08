package by.lawaksoft.tradebot.dto.model.publicdata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class InstrumentDto {

	private final String instrumentType;
	private final String instrumentId;
	private final String baseCurrency;
	private final String quoteCurrency;
	private final long listingTime;
	private final BigDecimal tickSize;
	private final BigDecimal lotSize;
	private final BigDecimal minOrderSize;
	private final BigDecimal maxLimitSize;
	private final BigDecimal maxMarketSize;
}
