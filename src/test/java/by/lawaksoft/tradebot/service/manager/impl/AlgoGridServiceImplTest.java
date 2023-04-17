package by.lawaksoft.tradebot.service.manager.impl;

import by.lawaksoft.tradebot.dto.AlgoInstanceDto;
import by.lawaksoft.tradebot.entity.AlgoType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AlgoGridServiceImplTest {

    @Autowired
    private AlgoGridServiceImpl algoGridService;

    @Test
    void execute() {
        AlgoInstanceDto algoInstanceDto = getAlgoInstanceDto();
        algoGridService.execute(algoInstanceDto);

        verify(algoGridService, times(1)).execute(algoInstanceDto);
    }

    private AlgoInstanceDto getAlgoInstanceDto() {
        return AlgoInstanceDto.builder()
                .algoType(getAlgoType())
                .build();
    }

    private AlgoType getAlgoType() {
        return AlgoType.builder()
                .id(1L)
                .name("Grid")
                .build();
    }
}