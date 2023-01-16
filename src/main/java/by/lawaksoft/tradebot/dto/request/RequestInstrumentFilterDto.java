package by.lawaksoft.tradebot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public class RequestInstrumentFilterDto {

	@JsonProperty("instType")
	@NonNull
	private final String instrumentType;

	public RequestInstrumentFilterDto(String instrumentType) {

		this.instrumentType = instrumentType;
	}
}
