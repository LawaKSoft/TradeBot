package by.lawaksoft.tradebot.document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collation = "candlestick")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Candlestick {

	@Id
	private String id;
	private String instId;
	private Long timestamp;
	private BigDecimal openPrice;
	private BigDecimal highestPrice;
	private BigDecimal lowestPrice;
	private BigDecimal closePrice;
	private BigDecimal volumeContract;
	private BigDecimal volCurrency;
	private BigDecimal volCurrencyQuote;
	private Boolean confirm;
}
