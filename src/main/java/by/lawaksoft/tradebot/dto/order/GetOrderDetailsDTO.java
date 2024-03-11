package by.lawaksoft.tradebot.dto.order;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class GetOrderDetailsDTO {

    private long id;
    private String instrumentType;
    private String instrumentId;
    private String quantityUnitService;
    private String orderId;
    private String clientOrderId;
    private String tag;
    private BigDecimal price;
    private double quantityToBuyOrSell;
    private double profitAndLoss;
    private String orderType;
    private String orderSide;
    private String positionSide;
    private String tradeMode;
    private String accumulatedFillQuantity;
    private String lastFilledPrice;
    private String lastTradeId;
    private String lastFilledQuantity;
    private String lastFilledTime;
    private String averageFilledPrice;
    private String state;
    private double takeProfitTriggerPrice;
    private String takeProfitTriggerPriceType;
    private double takeProfitOrderPrice;
    private double stopLossTriggerPrice;
    private String stopLossTriggerPriceType;
    private double stopLossOrderPrice;
    private String feeCurrency;
    private double feeAndRebate;
    private String rebateCurrency;
    private long source;
    private String rebate;
    private String category;
    private boolean reduceOnly;
    private long canceledSource;
    private String cancelSourceReason;
    private String unixTime;
    private String creationTime;
    private boolean banAmend;
    private String requestId;
}
