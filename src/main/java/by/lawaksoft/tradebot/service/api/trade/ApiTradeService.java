package by.lawaksoft.tradebot.service.api.trade;

import by.lawaksoft.tradebot.dto.order.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderDetailsDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderResponseDTO;
import by.lawaksoft.tradebot.dto.order.place_order.PlaceOrderRequestDTO;

public interface ApiTradeService {

    GetOrderResponseDTO placeOrder(PlaceOrderRequestDTO placeOrderRequestDTO);

    GetOrderDetailsDTO getOrderDetails(String instrumentId, String orderId, String clientOrderId);

    GetOrderResponseDTO cancelOrder(CancelOrderRequestDTO cancelOrderRequestDTO);

    GetOrderResponseDTO amendOrder(AmendOrderRequestDTO amendOrderRequestDTO);
}
