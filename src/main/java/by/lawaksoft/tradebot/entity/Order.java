package by.lawaksoft.tradebot.entity;

import by.lawaksoft.tradebot.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String instrumentType;
    private String instrumentId;
    private String quantityUnitService;
    private String orderId;
    private String clientOrderId;
    private String tag;
    private double price;
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

    @OneToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private Status status;
}