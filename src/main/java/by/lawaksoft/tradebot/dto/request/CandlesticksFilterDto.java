package by.lawaksoft.tradebot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
@AllArgsConstructor
public class CandlesticksFilterDto {

	@NonNull
	@JsonProperty("instId")
	private final String instId;
	@JsonProperty("bar")
	private final String bar;
	@JsonProperty("after")
	private final String after;
	@JsonProperty("before")
	private final String before;
	@JsonProperty("limit")
	private final String limit;
}
