package by.lawaksoft.tradebot.service.util.impl;

import by.lawaksoft.tradebot.dto.order.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.entity.enums.Method;
import by.lawaksoft.tradebot.mapper.JsonMapper;
import by.lawaksoft.tradebot.service.util.CreateTradeMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;

@Service
public class CreateTradeMessageServiceImpl implements CreateTradeMessageService {

    @Value("${ORDER_PATH}")
    private String ORDER_PATH;

    @Value("${CANCEL_ORDER_PATH}")
    private String CANCEL_ORDER_PATH;

    @Value("${AMEND_ORDER_PATH}")
    private String AMEN_ORDER_PATH;

    @Value("${BALANCE_PATH}")
    private String BALANCE_PATH;

    @Value("${ORDERS_HISTORY_PATH}")
    private String ORDERS_HISTORY_PATH;

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

    @Override
    public String getBalanceMessage(String timestamp) {
        return timestamp + Method.GET + BALANCE_PATH;
    }

    @Override
    public String getBalanceWithCurrenciesMessage(List<String> currencies, String timestamp) {
        StringJoiner currencyJoiner = new StringJoiner(",");
        currencies.forEach(currencyJoiner::add);
        return timestamp + Method.GET + BALANCE_PATH + "?ccy=" + currencyJoiner;
    }

    @Override
    public String getHistoryForWeekByInstrumentId(String instIds, String timestamp) {
        return timestamp + Method.GET + ORDERS_HISTORY_PATH + "?instId=" + instIds + "&";
    }

    @Override
    public String getHistoryForWeekByInstrumentsIds(List<String> instrumentIds, String timestamp) {
        return timestamp + Method.GET + ORDERS_HISTORY_PATH + "?instType=SPOT&instId=" + String.join(",", instrumentIds);
    }
}
