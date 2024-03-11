package by.lawaksoft.tradebot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
@AllArgsConstructor
@Builder
public class InstrumentsFilterDto {

	@NonNull
	@JsonProperty("instType")
	private final String instType;
	@JsonProperty("uly")
	private final String uly;
	@JsonProperty("instFamily")
	private final String instFamily;
	@JsonProperty("instId")
	private final String instId;
}
