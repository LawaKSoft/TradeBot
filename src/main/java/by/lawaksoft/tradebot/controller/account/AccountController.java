package by.lawaksoft.tradebot.controller.account;

import by.lawaksoft.tradebot.dto.account.GetBalanceResponseDTO;
import by.lawaksoft.tradebot.service.api.account.ApiAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final ApiAccountService apiAccountService;

    @Autowired
    public AccountController(ApiAccountService apiAccountService) {
        this.apiAccountService = apiAccountService;
    }

    @GetMapping("/balance")
    public ResponseEntity<GetBalanceResponseDTO> getBalance(@RequestParam(required = false, name = "ccy") List<String> currencies) {
        return ResponseEntity.ok(apiAccountService.getBalance(currencies));
    }

    @GetMapping("/test")
    public String test(@RequestParam(required = false, name = "ccy") List<String> currencies) {
        if (currencies == null) {
            System.out.println("null");
        }
        if (currencies.isEmpty()) {
            System.out.println("IsEmpty");
        }
        return null;
    }
}
