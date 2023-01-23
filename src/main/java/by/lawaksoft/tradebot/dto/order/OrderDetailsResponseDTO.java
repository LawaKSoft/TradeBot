package by.lawaksoft.tradebot.dto.order;

import lombok.*;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@ToString
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
    private String tpTriggerPx;
    private String tpTriggerPxType;
    private String tpOrdPx;
    private String slTriggerPx;
    private String slTriggerPxType;
    private String slOrdPx;
    private String feeCcy;
    private String fee;
    private String rebateCcy;
    private String rebate;
    private String tgtCcy;
    private String category;
    private boolean reduceOnly;
    private String cancelSource;
    private String cancelSourceReason;
    private String quickMgnType;
    private String uTime;
    private String cTime;
}
