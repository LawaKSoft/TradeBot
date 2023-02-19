package by.lawaksoft.tradebot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "balances")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private BigDecimal totalAmountOfEquity;
    private String updateTime;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "balance_currency_mapping",
            joinColumns = {@JoinColumn(name = "balance_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "currency_id", referencedColumnName = "id")})
    @MapKey(name = "currencyName")
    private Map<String, Currency> currencyMap;

    @OneToOne
    private User user;
}
