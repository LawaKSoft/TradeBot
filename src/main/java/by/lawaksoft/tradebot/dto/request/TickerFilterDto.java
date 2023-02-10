package by.lawaksoft.tradebot.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.NonNull;

@Getter
@AllArgsConstructor
@Builder
public class TickerFilterDto {

    @NonNull
    @JsonProperty("instType")
    private final String instrumentType;
    @JsonProperty("uly")
    private final String underlying;
    @JsonProperty("instId")
    private final String instrumentFamily;
}
