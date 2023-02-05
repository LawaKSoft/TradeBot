package by.lawaksoft.tradebot.client;

import by.lawaksoft.tradebot.dto.account.BalanceResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "${ACCOUNT}", url = "${ACCOUNT_URL}")
public interface AccountClient {

    @GetMapping("${BALANCE}")
    BalanceResponseDTO getBalance(@RequestHeader Map<String, String> header);

    @GetMapping("${BALANCE}")
    BalanceResponseDTO getBalanceWithCurrencies(@RequestParam(name = "ccy") List<String> currencies,
                                                @RequestHeader Map<String, String> header);
}
