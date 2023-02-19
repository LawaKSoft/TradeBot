package by.lawaksoft.tradebot.mapper;

import by.lawaksoft.tradebot.dto.account.BalanceResponseDTO;
import by.lawaksoft.tradebot.dto.account.GetBalanceResponseDTO;
import by.lawaksoft.tradebot.entity.Currency;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class AccountMapper {

    private AccountMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static List<GetBalanceResponseDTO> mapBalanceToGetBalanceResponseDTOList(List<BalanceResponseDTO> balanceList) {
        return balanceList.stream()
                .map(balance -> GetBalanceResponseDTO.builder()
                                .totalAmountOfEquity(new BigDecimal(balance.getTotalEq()))
                                .updateTime(balance.getUTime())
                                .currency(balance.getDetails().stream()
                                        .map(currency -> Currency.builder()
                                                .currencyName(currency.getCcy())
                                                .availableBalance(currency.getAvailBal())
                                                .cashBalance(new BigDecimal(currency.getCashBal()))
                                                .discountEquity(new BigDecimal(currency.getDisEq()))
                                                .equity(new BigDecimal(currency.getEq()))
                                                .equityInUSD(new BigDecimal(currency.getEqUsd()))
                                                .frozenBalance(new BigDecimal(currency.getFrozenBal()))
                                                .liabilities(new BigDecimal(currency.getLiab()))
                                                .uTime(balance.getUTime())
                                                .strategyEquity(new BigDecimal(currency.getStgyEq()))
                                                .build())
                                        .toList())
                                .build())
                .toList();
    }
}
