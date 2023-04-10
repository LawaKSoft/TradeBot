package by.lawaksoft.tradebot.dto.clientinfo;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class OkxClientParameterDto extends ClientParametersDto{

	private String okxApiKey;
	private String okxSecretKey;
	private String okxPassphrase;
}
