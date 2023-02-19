package by.lawaksoft.tradebot.service.api.account.impl;

import by.lawaksoft.tradebot.client.AccountClient;
import by.lawaksoft.tradebot.config.security.OkxConfigSecurity;
import by.lawaksoft.tradebot.config.security.SecurityService;
import by.lawaksoft.tradebot.dto.ResponseDTO;
import by.lawaksoft.tradebot.dto.account.BalanceDataResponseDTO;
import by.lawaksoft.tradebot.dto.account.BalanceResponseDTO;
import by.lawaksoft.tradebot.dto.account.GetBalanceResponseDTO;
import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.exception.dto.BusinessException;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApiAccountServiceImplTest {

    @Autowired
    private ApiAccountServiceImpl apiAccountService;
    @MockBean
    private AccountClient accountClient;

    private static final List<String> CURRENCIES = List.of("BTC");

    @Test
    void shouldGetBalanceWithCurrencies() {
        ResponseDTO<BalanceResponseDTO> balanceDTO = getBalanceDTO();

        when(accountClient.getBalanceWithCurrencies(any(), any())).thenReturn(balanceDTO);
        GetBalanceResponseDTO balance = apiAccountService.getBalance(CURRENCIES);

        assertEquals(0, balance.getId());
        assertEquals(0, balance.getCurrency().size());
        verify(accountClient, times(1)).getBalanceWithCurrencies(any(), any());
    }

    @Test
    void shouldGetBalanceWithOutCurrencies() {
        ResponseDTO<BalanceResponseDTO> balanceDTO = getBalanceDTO();

        when(accountClient.getBalance(any())).thenReturn(balanceDTO);
        GetBalanceResponseDTO balance = apiAccountService.getBalance(null);

        assertEquals(0, balance.getId());
        assertEquals(0, balance.getCurrency().size());
        verify(accountClient, times(1)).getBalance(any());
    }

    @Test
    void shouldThrowExWhenFeignGetBalance() {
        when(accountClient.getBalance(any())).thenThrow(FeignException.class);

        assertThrows(BusinessException.class, () -> apiAccountService.getBalance(null));
    }


    private ResponseDTO<BalanceResponseDTO> getBalanceDTO() {
        return ResponseDTO.
                <BalanceResponseDTO> builder()
                .data(List.of(BalanceResponseDTO.builder()
                        .totalEq("0")
                        .details(new ArrayList<>())
                        .build()))
                .build();
    }

    private User getUser() {
        return User.builder()
                .id(1)
                .build();
    }
}