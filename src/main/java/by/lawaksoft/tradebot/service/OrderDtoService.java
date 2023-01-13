package by.lawaksoft.tradebot.service;

import by.lawaksoft.tradebot.dto.order.GetOrderDetailsDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderResponseDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderResponseDTO;
import org.springframework.http.HttpMethod;

public interface OrderDtoService {

    GetOrderResponseDTO placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO);

    GetOrderDetailsDTO getOrderDetails(String instrumentId, String orderId, String clientOrderId);
}
