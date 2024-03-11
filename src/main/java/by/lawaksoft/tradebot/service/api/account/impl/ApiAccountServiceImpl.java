package by.lawaksoft.tradebot.service.api.account.impl;

import by.lawaksoft.tradebot.client.AccountClient;
import by.lawaksoft.tradebot.config.security.OkxConfigSecurity;
import by.lawaksoft.tradebot.dto.ResponseDTO;
import by.lawaksoft.tradebot.dto.account.BalanceResponseDTO;
import by.lawaksoft.tradebot.dto.account.GetBalanceResponseDTO;
import by.lawaksoft.tradebot.exception.dto.BusinessException;
import by.lawaksoft.tradebot.exception.dto.enums.ERROR_MESSAGE;
import by.lawaksoft.tradebot.service.api.account.ApiAccountService;
import by.lawaksoft.tradebot.service.util.CreateTradeMessageService;
import by.lawaksoft.tradebot.util.TimeManager;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static by.lawaksoft.tradebot.mapper.AccountMapper.mapBalanceToGetBalanceResponseDTOList;

@Service
public class ApiAccountServiceImpl implements ApiAccountService {

    private final AccountClient accountClient;
    private final CreateTradeMessageService createTradeMessageService;
    private final OkxConfigSecurity okxConfigSecurity;

    private static final String BAD_FEIGN_REQUEST = "Bad feign request %s";

    @Autowired
    public ApiAccountServiceImpl(AccountClient accountClient, CreateTradeMessageService createTradeMessageService,
                                 OkxConfigSecurity okxConfigSecurity) {
        this.accountClient = accountClient;
        this.createTradeMessageService = createTradeMessageService;
        this.okxConfigSecurity = okxConfigSecurity;
    }

    @Override
    public GetBalanceResponseDTO getBalance(List<String> currencies) {
        ResponseDTO<BalanceResponseDTO> responseDTO;
        try {
            if (currencies == null) {
                responseDTO = accountClient.getBalance(getHeaderForBalance(TimeManager.getTimestampForOkx()));
            } else {
                responseDTO = accountClient.getBalanceWithCurrencies(currencies, getHeaderForBalanceWithCurrencies(currencies,
                        TimeManager.getTimestampForOkx()));
            }
        } catch (FeignException e) {
            throw new BusinessException(String.format(BAD_FEIGN_REQUEST, e.getMessage()), ERROR_MESSAGE.BAD_REQUEST);
        }

        return mapBalanceToGetBalanceResponseDTOList(responseDTO.getData()).get(0);
    }


    private Map<String, String> getHeaderForBalance(String timestamp) {
        String message = createTradeMessageService.getBalanceMessage(timestamp);
        return okxConfigSecurity.getHeader(message, timestamp);
    }

    private Map<String, String> getHeaderForBalanceWithCurrencies(List<String> currencies, String timestamp) {
        String message = createTradeMessageService.getBalanceWithCurrenciesMessage(currencies, timestamp);
        return okxConfigSecurity.getHeader(message, timestamp);
    }
}
