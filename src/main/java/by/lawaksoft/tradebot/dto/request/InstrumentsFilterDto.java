package by.lawaksoft.tradebot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

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

	@NonNull
	public String getInstType() {

		return instType;
	}

	public String getUly() {

		return uly;
	}

	public String getInstFamily() {

		return instFamily;
	}

	public String getInstId() {

		return instId;
	}
}
