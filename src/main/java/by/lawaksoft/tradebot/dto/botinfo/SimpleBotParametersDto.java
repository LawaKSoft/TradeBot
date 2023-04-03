package by.lawaksoft.tradebot.dto.botinfo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Setter
@Getter
@SuperBuilder
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
