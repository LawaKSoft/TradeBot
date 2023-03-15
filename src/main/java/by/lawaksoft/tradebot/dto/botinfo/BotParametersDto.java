package by.lawaksoft.tradebot.dto.botinfo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BotParametersDto {

	private String tradeMarketPare;

	public BotParametersDto(String tradeMarketPare) {

		this.tradeMarketPare = tradeMarketPare;
	}
}
