package by.lawaksoft.tradebot.service.util;

import by.lawaksoft.tradebot.dto.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;

public interface CreateTradeMessageService {

    String placeOrderMessage(PlaceOrderRequestDTO placeOrderRequestDTO, String timestamp);

    String getOrderDetailsMessage(String instrumentId, String orderId, String clientOrderId, String timestamp);

    String cancelOrderMessage(CancelOrderRequestDTO cancelOrderRequestDTO, String timestamp);
}
