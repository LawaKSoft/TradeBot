package by.lawaksoft.tradebot.service.entity.impl;

import by.lawaksoft.tradebot.entity.Balance;
import by.lawaksoft.tradebot.exception.dto.BusinessException;
import by.lawaksoft.tradebot.exception.dto.enums.ERROR_MESSAGE;
import by.lawaksoft.tradebot.repository.BalanceRepository;
import by.lawaksoft.tradebot.service.entity.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final BalanceRepository balanceRepository;

    @Autowired
    public AccountServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @Override
    public Balance findBalanceByUserId(long userId) {
        return balanceRepository.findBalanceByUserId(userId)
                .orElseThrow(() -> new BusinessException(String.format("Balance with userId %s not found", userId),
                                                            ERROR_MESSAGE.BALANCE_NOT_FOUND, HttpStatus.NOT_FOUND));
    }
}
