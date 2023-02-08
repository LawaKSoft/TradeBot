package by.lawaksoft.tradebot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
public class TickerFilterDto {

    @NonNull
    private final String instrumentType;
    private final String underlying;
    private final String instrumentFamily;

    public TickerFilterDto(@JsonProperty("instType") String instrumentType, @JsonProperty("uly") String underlying, @JsonProperty("instId") String instrumentFamily) {
        this.instrumentType = instrumentType;
        this.underlying = underlying;
        this.instrumentFamily = instrumentFamily;
    }
}
