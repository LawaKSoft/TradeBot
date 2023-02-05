package by.lawaksoft.tradebot.service.api.account.impl;

import by.lawaksoft.tradebot.client.AccountClient;
import by.lawaksoft.tradebot.config.security.OkxConfigSecurity;
import by.lawaksoft.tradebot.config.security.SecurityService;
import by.lawaksoft.tradebot.dto.account.BalanceResponseDTO;
import by.lawaksoft.tradebot.dto.account.GetBalanceResponseDTO;
import by.lawaksoft.tradebot.entity.Balance;
import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.exception.dto.BusinessException;
import by.lawaksoft.tradebot.exception.dto.enums.ERROR_MESSAGE;
import by.lawaksoft.tradebot.service.api.account.ApiAccountService;
import by.lawaksoft.tradebot.service.entity.AccountService;
import by.lawaksoft.tradebot.service.util.CreateTradeMessageService;
import by.lawaksoft.tradebot.util.TimeManager;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiAccountServiceImpl implements ApiAccountService {

    private final AccountService accountService;
    private final SecurityService securityService;
    private final AccountClient accountClient;
    private final CreateTradeMessageService createTradeMessageService;
    private final OkxConfigSecurity okxConfigSecurity;

    private static final String BAD_FEIGN_REQUEST = "Bad feign request %s";

    @Autowired
    public ApiAccountServiceImpl(AccountService accountService, SecurityService securityService, AccountClient accountClient, CreateTradeMessageService createTradeMessageService, OkxConfigSecurity okxConfigSecurity) {
        this.accountService = accountService;
        this.securityService = securityService;
        this.accountClient = accountClient;
        this.createTradeMessageService = createTradeMessageService;
        this.okxConfigSecurity = okxConfigSecurity;
    }

    @Override
    public GetBalanceResponseDTO getBalance(List<String> currencies) {
        User user = getUser();

        Balance balance = accountService.findBalanceByUserId(user.getId());

        BalanceResponseDTO balanceResponseDTO;
        try {
            if (currencies == null) {
                balanceResponseDTO = accountClient.getBalance(getHeaderForBalance(TimeManager.getTimestampForOkx()));
            } else {
                balanceResponseDTO = accountClient.getBalanceWithCurrencies(currencies, getHeaderForBalanceWithCurrencies(currencies,
                                                                                                        TimeManager.getTimestampForOkx()));
            }
        } catch (FeignException e) {
            throw new BusinessException(String.format(BAD_FEIGN_REQUEST, e.getMessage()), ERROR_MESSAGE.BAD_REQUEST);
        }

        //TODO: доделать проверки на валюты, подумать о том как будут храниться валюты

        return null;
    }


    private Map<String, String> getHeaderForBalance(String timestamp) {
        String message = createTradeMessageService.getBalanceMessage(timestamp);
        return okxConfigSecurity.getHeader(message, timestamp);
    }

    private Map<String, String> getHeaderForBalanceWithCurrencies(List<String> currencies, String timestamp) {
        String message = createTradeMessageService.getBalanceWithCurrenciesMessage(currencies, timestamp);
        return okxConfigSecurity.getHeader(message, timestamp);
    }

    private User getUser() {
        return securityService.getUser();
    }
}
