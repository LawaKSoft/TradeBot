package by.lawaksoft.tradebot.dto.botinfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class SimpleBotParametersDto extends BotParametersDto {

	private BigDecimal accountBalance;
	private BigDecimal upperGridRange;
	private BigDecimal lowerGridRange;
	private BigDecimal stepsCounts;

	public SimpleBotParametersDto(String tradeMarketPare, BigDecimal accountBalance, BigDecimal upperGridRange, BigDecimal lowerGridRange, BigDecimal stepsCounts) {

		super(tradeMarketPare);
		this.accountBalance = accountBalance;
		this.upperGridRange = upperGridRange;
		this.lowerGridRange = lowerGridRange;
		this.stepsCounts = stepsCounts;
	}
}
