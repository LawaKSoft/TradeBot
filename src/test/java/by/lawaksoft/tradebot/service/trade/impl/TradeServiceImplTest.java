package by.lawaksoft.tradebot.service.trade.impl;

import by.lawaksoft.tradebot.client.TradeClient;
import by.lawaksoft.tradebot.config.security.OkxConfigSecurity;
import by.lawaksoft.tradebot.config.security.SecurityService;
import by.lawaksoft.tradebot.dto.order.*;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.entity.Order;
import by.lawaksoft.tradebot.entity.User;
import by.lawaksoft.tradebot.entity.enums.Status;
import by.lawaksoft.tradebot.exception.entity.BusinessException;
import by.lawaksoft.tradebot.service.entity.OrderService;
import by.lawaksoft.tradebot.service.util.CreateTradeMessageService;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TradeServiceImplTest {

    @Autowired
    private TradeServiceImpl tradeService;

    @MockBean
    private OrderService orderService;
    @MockBean
    private CreateTradeMessageService createTradeMessageService;
    @MockBean
    private OkxConfigSecurity okxConfigSecurity;
    @MockBean
    private TradeClient tradeClient;
    @MockBean
    private SecurityService securityService;

    private final static String INST_ID = "instId";
    private final static String ORDER_ID = "orderId";
    private final static String CLIENT_ORDER_ID = "clientOrderId";
    private final static String TAG = "tag";

    @Test
    void shouldPlaceOrder() {
        PlaceOrderRequestDTO placeOrderRequestDTO = getPlaceOrderRequestDTO();
        OrderResponseDTO orderResponseDTO = getOrderResponseDTO();
        User user = getUser();
        Order order = Order.builder()
                .instrumentId(INST_ID)
                .orderId(ORDER_ID)
                .clientOrderId(CLIENT_ORDER_ID)
                .price(1)
                .quantityToBuyOrSell(1)
                .tag(TAG)
                .user(user)
                .status(Status.ACTIVE)
                .build();

        when(securityService.getUser()).thenReturn(user);
        when(tradeClient.placeOrder(any(), any())).thenReturn(orderResponseDTO);
        when(orderService.save(order)).thenReturn(order);

        GetOrderResponseDTO getOrderResponseDTO = tradeService.placeOrder(placeOrderRequestDTO);

        assertEquals(getOrderResponseDTO.getOrderId(), ORDER_ID);
        assertEquals(getOrderResponseDTO.getClientOrderId(), CLIENT_ORDER_ID);
        verify(orderService, times(1)).save(order);
        verify(tradeClient, times(1)).placeOrder(any(), any());
    }

    @Test
    void shouldThrowExWhenFeignRequestPlaceOrder() {
        PlaceOrderRequestDTO placeOrderRequestDTO = getPlaceOrderRequestDTO();
        OrderResponseDTO orderResponseDTO = getOrderResponseDTO();
        User user = getUser();
        Order order = Order.builder()
                .instrumentId(INST_ID)
                .orderId(ORDER_ID)
                .clientOrderId(CLIENT_ORDER_ID)
                .price(1)
                .quantityToBuyOrSell(1)
                .tag(TAG)
                .user(user)
                .status(Status.ACTIVE)
                .build();

        when(securityService.getUser()).thenReturn(user);
        when(tradeClient.placeOrder(any(), any())).thenThrow(FeignException.class);
        assertThrows(BusinessException.class, () -> tradeService.placeOrder(placeOrderRequestDTO));
    }

    @Test
    void shouldReturnOrderDetails() {
        OrderDetailsResponseDTO orderDetailsResponseDTO = getOrderDetailsResponseDTO();
        User user = getUser();
        Order order = Order.builder()
                .user(user)
                .id(1)
                .build();

        when(securityService.getUser()).thenReturn(user);
        when(orderService.findOrderByOrderIdAndUserId(ORDER_ID, 1)).thenReturn(order);
        when(orderService.save(order)).thenReturn(order);
        when(tradeClient.getOrderDetails(any(), any(), any(), any())).thenReturn(orderDetailsResponseDTO);

        GetOrderDetailsDTO result = tradeService.getOrderDetails(INST_ID, ORDER_ID, CLIENT_ORDER_ID);

        assertEquals(result.getId(), 1);
        verify(orderService).findOrderByOrderIdAndUserId(ORDER_ID, 1);
        verify(securityService).getUser();
        verify(orderService, times(1)).findOrderByOrderIdAndUserId(ORDER_ID, 1);
        verify(tradeClient, times(1)).getOrderDetails(any(), any(), any(), any());
    }

    @Test
    void shouldThrowExWhenFeignRequestGetDetails () {
        when(tradeClient.getOrderDetails(any(), any(), any(), any())).thenThrow(FeignException.class);
        assertThrows(BusinessException.class, () -> tradeService.getOrderDetails(INST_ID, ORDER_ID, CLIENT_ORDER_ID));
    }

    private PlaceOrderRequestDTO getPlaceOrderRequestDTO() {
        return PlaceOrderRequestDTO.builder()
                .instId(INST_ID)
                .px("1")
                .sz("1")
                .build();
    }

    private OrderResponseDTO getOrderResponseDTO() {
        return OrderResponseDTO.builder()
                .data(List.of(getoOrderDataResponseDTO()))
                .build();
    }

    private OrderDataResponseDTO getoOrderDataResponseDTO() {
        return OrderDataResponseDTO.builder()
                .ordId(ORDER_ID)
                .clOrdId(CLIENT_ORDER_ID)
                .tag(TAG)
                .build();
    }

    private User getUser() {
        return User.builder()
                .id(1)
                .build();
    }

    private OrderDetailsResponseDTO getOrderDetailsResponseDTO() {
        return OrderDetailsResponseDTO.builder()
                .ordId(ORDER_ID)
                .build();

    }

    private GetOrderDetailsDTO getGetOrderDetailsDTO() {
        return GetOrderDetailsDTO.builder()
                .id(1)
                .orderId(ORDER_ID)
                .build();
    }
}
