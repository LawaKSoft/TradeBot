package by.lawaksoft.tradebot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String currencyName;
    private String availableBalance;
    private BigDecimal availableEquity;
    private BigDecimal cashBalance;
    private BigDecimal crossLiabilities;
    private BigDecimal discountEquity;
    private BigDecimal equity;
    private BigDecimal equityInUSD;
    private BigDecimal frozenBalance;
    private BigDecimal interest;
    private BigDecimal isolatedMarginEquity;
    private BigDecimal isolatedLiabilities;
    private BigDecimal isolatedUnrealizedProfitNLoss;
    private BigDecimal liabilities;
    private BigDecimal maxLoan;
    private String marginRatio;
    private String notionalLever;
    private BigDecimal marginFrozenForOpenOrders;
    private int twap;
    private BigDecimal uTime;
    private BigDecimal sumOfUnrealizedProfitNLoss;
    private BigDecimal liabilitiesDueToUnrealizedLoss;
    private BigDecimal strategyEquity;
    private String spotInUseAmount;
}
