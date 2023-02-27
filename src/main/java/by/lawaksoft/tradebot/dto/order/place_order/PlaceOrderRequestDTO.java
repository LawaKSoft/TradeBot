package by.lawaksoft.tradebot.dto.order.place_order;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PlaceOrderRequestDTO {

    private String instId;
    private String tdMode;
    private String clOrdId;
    private String side;
    private String ordType;
    private BigDecimal px;
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


    public PlaceOrderRequestDTO(String instId, String tdMode, String side, String ordType, BigDecimal px) {
        this.instId = instId;
        this.tdMode = tdMode;
        this.side = side;
        this.ordType = ordType;
        this.px = px;
    }
}
