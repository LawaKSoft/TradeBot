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
    private BigDecimal cashBalance;
    private BigDecimal discountEquity;
    private BigDecimal equity;
    private BigDecimal equityInUSD;
    private BigDecimal frozenBalance;
    private BigDecimal liabilities;
    private String uTime;
    private BigDecimal strategyEquity;
}
