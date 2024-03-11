package by.lawaksoft.tradebot.service.entity.impl;

import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.exception.dto.BusinessException;
import by.lawaksoft.tradebot.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TradeTradeOrderServiceImplTest {

    @InjectMocks
    private TradeOrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    private final static String ORDER_ID = "orderId";
    private final static long USER_ID = 1;

    @Test
    void shouldSaveAndReturnSaveOrder() {
        Order testOrder = getOrder();
        when(orderRepository.save(testOrder)).thenReturn(testOrder);
        Order order = orderService.save(testOrder);

        assertEquals(order, testOrder);
        verify(orderRepository).save(testOrder);
        verify(orderRepository, times(1)).save(testOrder);
        verifyNoMoreInteractions(orderRepository);
    }

    @Test
    void shouldReturnOrderByOrderIdAndUserId() {
        Order testOrder = getOrder();
        when(orderRepository.findByOrderIdAndUserId(ORDER_ID, USER_ID)).thenReturn(Optional.ofNullable(testOrder));

        Order order = orderService.findOrderByOrderIdAndUserId(ORDER_ID, USER_ID);

        assertEquals(order, testOrder);
        verify(orderRepository).findByOrderIdAndUserId(ORDER_ID, USER_ID);
        verify(orderRepository, times(1)).findByOrderIdAndUserId(ORDER_ID, USER_ID);
        verifyNoMoreInteractions(orderRepository);
    }

    @Test
    void shouldThrowExWhenOrderNotFoundByOrderIdAndUserId() {
        when(orderRepository.findByOrderIdAndUserId(ORDER_ID, USER_ID)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> orderService.findOrderByOrderIdAndUserId(ORDER_ID, USER_ID));
        verify(orderRepository).findByOrderIdAndUserId(ORDER_ID, USER_ID);
        verify(orderRepository, times(1)).findByOrderIdAndUserId(ORDER_ID, USER_ID);
        verifyNoMoreInteractions(orderRepository);
    }


    private Order getOrder() {
        User user = getUser();
        return Order.builder()
                .orderId(ORDER_ID)
                .id(1)
                .user(user)
                .build();
    }

    private User getUser() {
        return User.builder()
                .id(1)
                .build();
    }
}