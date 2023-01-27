package by.lawaksoft.tradebot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public class CandlesticksFilterDto {

	@NonNull
	private final String instId;
	private final String bar;
	private final String after;
	private final String before;
	private final String limit;

	public CandlesticksFilterDto(@JsonProperty("instId") String instId, @JsonProperty("bar") String bar, @JsonProperty("after") String after, @JsonProperty("before") String before,
			@JsonProperty("limit") String limit) {

		this.instId = instId;
		this.bar = bar;
		this.after = after;
		this.before = before;
		this.limit = limit;
	}

	public String getInstId() {

		return instId;
	}

	public String getBar() {

		return bar;
	}

	public String getAfter() {

		return after;
	}

	public String getBefore() {

		return before;
	}

	public String getLimit() {

		return limit;
	}
}
