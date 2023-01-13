package by.lawaksoft.tradebot.dto.place_order;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaceOrderRequestDTO {

    private String instId;
    private String tdMode;
    private String ccy;
    private String clOrdId;
    private String side;
    private String ordType;
    private String px;
    private String sz;
    private String tag;
    private String posSide;
    private boolean reduceOnly;
    private String tgtCcy;
    private boolean banAmend;
    private double tpOrdPx;
    private double tpTriggerPx;
    private double slTriggerPx;
    private double slOrdPx;
    private String tpTriggerPxType;
    private String slTriggerPxType;
    private String quickMgnType;

    public PlaceOrderRequestDTO(String instId, String tdMode, String side, String ordType, String px) {
        this.instId = instId;
        this.tdMode = tdMode;
        this.side = side;
        this.ordType = ordType;
        this.px = px;
    }
}
