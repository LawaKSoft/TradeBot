package by.lawaksoft.tradebot.dto.model.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class TickerDto {

	private final String instrumentType;
	private final String instrumentId;
	private final BigDecimal lastPrice;
	private final BigDecimal lastSize;
	private final BigDecimal askPrice;
	private final BigDecimal askSize;
	private final BigDecimal bestPrice;
	private final BigDecimal bestSize;
	private final BigDecimal open24hPrice;
	private final BigDecimal highest24hPrice;
	private final BigDecimal lowest24h;
	private final BigDecimal volume24hCurrency;
	private final BigDecimal volume24hContract;
	private final BigDecimal priceUTC0;
	private final BigDecimal priceUTC8;
	private final long timestamp;
}
