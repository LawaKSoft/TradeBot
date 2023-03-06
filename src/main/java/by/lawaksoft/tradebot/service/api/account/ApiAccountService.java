package by.lawaksoft.tradebot.service.api.account;

import by.lawaksoft.tradebot.dto.account.GetBalanceResponseDTO;

import java.util.List;

public interface ApiAccountService {

    GetBalanceResponseDTO getBalance(List<String> currency);
}
