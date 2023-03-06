package by.lawaksoft.tradebot.dto.account;

import by.lawaksoft.tradebot.entity.Currency;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
public class GetBalanceResponseDTO {

    private long id;
    private BigDecimal totalAmountOfEquity;
    private String updateTime;
    private List<Currency> currency;
}
