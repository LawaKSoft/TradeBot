package by.lawaksoft.tradebot.service.util;

import by.lawaksoft.tradebot.dto.order.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.place_order.PlaceOrderRequestDTO;

import java.util.List;

public interface CreateTradeMessageService {

    String placeOrderMessage(PlaceOrderRequestDTO placeOrderRequestDTO, String timestamp);

    String getOrderDetailsByOrderIdMessage(String instrumentId, String orderId, String timestamp);

    String getOrderDetailsByClientOrderIdMessage(String instrumentId, String clientOrderId, String timestamp);

    String cancelOrderMessage(CancelOrderRequestDTO cancelOrderRequestDTO, String timestamp);

    String amendOrderMessage(AmendOrderRequestDTO amendOrderRequestDTO, String timestamp);

    String getBalanceMessage(String timestamp);

    String getBalanceWithCurrenciesMessage(List<String> currencies, String timestamp);
}
