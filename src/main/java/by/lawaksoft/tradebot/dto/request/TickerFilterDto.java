package by.lawaksoft.tradebot.dto.request;

import org.springframework.lang.NonNull;

public class TickerFilterDto {

    @NonNull
    private String instrumentType;

    private String underlying;
    private String instrumentFamily;

    public TickerFilterDto(String instrumentType) {

        this.instrumentFamily = instrumentType;
    }

    public TickerFilterDto(@NonNull String instrumentType, String underlying, String instrumentFamily) {
        this.instrumentType = instrumentType;
        this.underlying = underlying;
        this.instrumentFamily = instrumentFamily;
    }

    @NonNull
    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(@NonNull String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getUnderlying() {
        return underlying;
    }

    public void setUnderlying(String underlying) {
        this.underlying = underlying;
    }

    public String getInstrumentFamily() {
        return instrumentFamily;
    }

    public void setInstrumentFamily(String instrumentFamily) {
        this.instrumentFamily = instrumentFamily;
    }
}
