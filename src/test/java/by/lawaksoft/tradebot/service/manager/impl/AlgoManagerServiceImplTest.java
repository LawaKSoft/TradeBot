package by.lawaksoft.tradebot.service.manager.impl;

import by.lawaksoft.tradebot.beanlocator.AlgorithmBeanLocator;
import by.lawaksoft.tradebot.dto.AlgoInstanceDto;
import by.lawaksoft.tradebot.entity.AlgoType;
import by.lawaksoft.tradebot.entity.enums.AlgorithmBot;
import by.lawaksoft.tradebot.exception.dto.BusinessException;
import org.junit.jupiter.api.*;
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
class AlgoManagerServiceImplTest {

    @Autowired
    private AlgoManagerServiceImpl algoManagerService;

    @MockBean
    private AlgorithmBeanLocator algorithmBeanLocator;

    private static MockedStatic<SecurityContextHolder> securityContextHolderMockedStatic;

    @BeforeAll
    static void beforeAll() {

        securityContextHolderMockedStatic = mockStatic(SecurityContextHolder.class);
    }

    @AfterAll
    static void afterAll() {

        securityContextHolderMockedStatic.close();
    }

    @Test
    void shouldRun() {

        AlgoInstanceDto algoInstanceDto = getAlgoInstanceDto();

        when(algorithmBeanLocator.getAlgo(AlgorithmBot.GRID)).thenReturn(getAlgoGridService());

        algoManagerService.run(algoInstanceDto);

        verify(algoManagerService, times(1)).run(algoInstanceDto);
        verify(algorithmBeanLocator, times(1)).getAlgo(AlgorithmBot.GRID);
    }

    @Test
    void shouldThrowBusExByEnum() {

        AlgoInstanceDto algoInstanceDto = AlgoInstanceDto.builder()
                .algoType(AlgoType.builder()
                        .name("")
                        .build())
                .build();
        assertThrows(BusinessException.class, () -> algoManagerService.run(algoInstanceDto));
    }


    private AlgoGridServiceImpl getAlgoGridService() {

        return new AlgoGridServiceImpl();
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

    private void mockSecurityContextHolder() {

        SecurityContext mockSecurityContext = mock(SecurityContext.class);
        securityContextHolderMockedStatic.when(SecurityContextHolder::getContext).thenReturn(mockSecurityContext);
        Authentication authentication = mock(Authentication.class);
        when(mockSecurityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("username");
    }
}