package by.lawaksoft.tradebot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public class InstrumentsFilterDto {

	@NonNull
	private final String instType;
	private final String uly;
	private final String instFamily;
	private final String instId;

	public InstrumentsFilterDto(@JsonProperty("instType") String instType, @JsonProperty("uly") String uly, @JsonProperty("instFamily") String instFamily,
			@JsonProperty("instId") String instId) {

		this.instType = instType;
		this.uly = uly;
		this.instFamily = instFamily;
		this.instId = instId;
	}
}
