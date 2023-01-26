package by.lawaksoft.tradebot.dto.order;

import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class OrderDetailsResponseDTO {

    private String instType;
    private String instId;
    private String ccy;
    private String ordId;
    private String clOrdId;
    private String tag;
    private double px;
    private double sz;
    private double pnl;
    private String ordType;
    private String side;
    private String posSide;
    private String tdMode;
    private String accFillSz;
    private String fillPx;
    private String tradeId;
    private String fillSz;
    private String fillTime;
    private String state;
    private String avgPx;
    private String lever;
    private double tpTriggerPx;
    private String tpTriggerPxType;
    private double tpOrdPx;
    private double slTriggerPx;
    private String slTriggerPxType;
    private double  slOrdPx;
    private String feeCcy;
    private double fee;
    private String rebateCcy;
    private String rebate;
    private String tgtCcy;
    private String category;
    private boolean reduceOnly;
    private long cancelSource;
    private String cancelSourceReason;
    private String quickMgnType;
    private long source;
    private String uTime;
    private String cTime;
}
