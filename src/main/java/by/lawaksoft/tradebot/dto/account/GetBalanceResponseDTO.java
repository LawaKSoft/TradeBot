package by.lawaksoft.tradebot.dto.account;

import by.lawaksoft.tradebot.entity.Currency;
import lombok.Builder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Builder
public class GetBalanceResponseDTO {

    private long id;
    private BigDecimal totalAmountOfEquity;
    private BigInteger updateTime;
    private List<Currency> currency;
}
