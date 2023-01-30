package by.lawaksoft.tradebot.service.util.impl;

import by.lawaksoft.tradebot.dto.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.entity.enums.Method;
import by.lawaksoft.tradebot.mapper.JsonMapper;
import by.lawaksoft.tradebot.service.util.CreateTradeMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreateTradeMessageServiceImpl implements CreateTradeMessageService {

    private final static String ORDER_PATH = "/api/v5/trade/order";
    private final static String CANCEL_ORDER_PATH = "/api/v5/trade/cancel-order";
    private final static String AMEN_ORDER_PATH = "/api/v5/trade/amend-order";

    @Value("${ORDER_ID}")
    private String ORDER_ID;

    @Value("${CLIENT_ORDER_ID}")
    private String CLIENT_ORDER_ID;

    @Value("${INSTRUMENT_ID}")
    private String INSTRUMENT_ID;

    @Override
    public String placeOrderMessage(PlaceOrderRequestDTO placeOrderRequestDTO, String timestamp) {
        return timestamp + Method.POST + ORDER_PATH + JsonMapper.objectToJson(placeOrderRequestDTO);
    }

    @Override
    public String getOrderDetailsByOrderIdMessage(String instrumentId, String orderId, String timestamp) {
        return timestamp + Method.GET + ORDER_PATH + "?" + INSTRUMENT_ID + "=" + instrumentId + "&" + ORDER_ID + "=" + orderId;
    }

    @Override
    public String getOrderDetailsByClientOrderIdMessage(String instrumentId, String clientOrderId, String timestamp) {
        return timestamp + Method.GET + ORDER_PATH + "?" + INSTRUMENT_ID + "=" + instrumentId + "&" + CLIENT_ORDER_ID + "=" + clientOrderId;
    }

    @Override
    public String cancelOrderMessage(CancelOrderRequestDTO cancelOrderRequestDTO, String timestamp) {
        return timestamp + Method.POST + CANCEL_ORDER_PATH + JsonMapper.objectToJson(cancelOrderRequestDTO);
    }

    @Override
    public String amendOrderMessage(AmendOrderRequestDTO amendOrderRequestDTO, String timestamp) {
        return timestamp + Method.POST + AMEN_ORDER_PATH + JsonMapper.objectToJson(amendOrderRequestDTO);
    }
}
