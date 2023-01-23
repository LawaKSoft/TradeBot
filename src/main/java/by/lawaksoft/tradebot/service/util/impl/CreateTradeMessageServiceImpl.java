package by.lawaksoft.tradebot.service.util.impl;

import by.lawaksoft.tradebot.client.ClientNavigation;
import by.lawaksoft.tradebot.dto.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.entity.enums.Methode;
import by.lawaksoft.tradebot.mapper.JsonMapper;
import by.lawaksoft.tradebot.service.util.CreateTradeMessageService;
import org.springframework.stereotype.Service;

@Service
public class CreateTradeMessageServiceImpl implements CreateTradeMessageService {

    private final static String ORDER_PATH = "/api/v5/trade/order";
    private final static String CANCEL_ORDER_PATH = "/api/v5/trade/cancel-order";

    @Override
    public String placeOrderMessage(PlaceOrderRequestDTO placeOrderRequestDTO, String timestamp) {
        return timestamp + Methode.POST + ORDER_PATH + JsonMapper.objectToJson(placeOrderRequestDTO);
    }

    @Override
    public String getOrderDetailsMessage(String instrumentId, String orderId, String clientOrderId, String timestamp) {
        return timestamp + Methode.GET + ORDER_PATH + "?" + ClientNavigation.ORDER_ID + "=" + orderId + "&" +
                                                            ClientNavigation.CLIENT_ORDER_ID + "=" + clientOrderId + "&" +
                                                            ClientNavigation.INSTRUMENT_ID + "=" + instrumentId;
    }

    @Override
    public String cancelOrderMessage(CancelOrderRequestDTO cancelOrderRequestDTO, String timestamp) {
        return timestamp + Methode.POST + CANCEL_ORDER_PATH + JsonMapper.objectToJson(cancelOrderRequestDTO);
    }
}
