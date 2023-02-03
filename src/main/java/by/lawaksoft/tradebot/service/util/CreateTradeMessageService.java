package by.lawaksoft.tradebot.service.util;

import by.lawaksoft.tradebot.dto.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;

public interface CreateTradeMessageService {

    String placeOrderMessage(PlaceOrderRequestDTO placeOrderRequestDTO, String timestamp);

    String getOrderDetailsByOrderIdMessage(String instrumentId, String orderId, String timestamp);

    String getOrderDetailsByClientOrderIdMessage(String instrumentId, String clientOrderId, String timestamp);

    String cancelOrderMessage(CancelOrderRequestDTO cancelOrderRequestDTO, String timestamp);

    String amendOrderMessage(AmendOrderRequestDTO amendOrderRequestDTO, String timestamp);
}
