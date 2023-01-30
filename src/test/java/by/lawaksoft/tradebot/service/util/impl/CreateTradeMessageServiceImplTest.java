package by.lawaksoft.tradebot.service.util.impl;

import by.lawaksoft.tradebot.dto.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.entity.enums.Method;
import by.lawaksoft.tradebot.service.util.CreateTradeMessageService;
import by.lawaksoft.tradebot.util.TimeManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateTradeMessageServiceImplTest {

    @Autowired
    private CreateTradeMessageService createTradeMessageService;

    @Autowired
    private ObjectMapper objectMapper;

    private final static String INST_ID = "instId";
    private final static String ORDER_ID = "orderId";
    private final static String CLIENT_ORDER_ID = "clientOrderId";
    private final static String ORDER_PATH = "/api/v5/trade/order";
    private final static String CANCEL_ORDER_PATH = "/api/v5/trade/cancel-order";
    private final static String AMEN_ORDER_PATH = "/api/v5/trade/amend-order";

    @Test
    void placeOrderMessage() {
        PlaceOrderRequestDTO placeOrderRequestDTO = getPlaceOrderRequestDTO();
        String timestamp = TimeManager.getTimestampForOkx();
        String createMessage = createTradeMessageService.placeOrderMessage(placeOrderRequestDTO, timestamp);
        String message = timestamp + Method.POST + ORDER_PATH + convertToJson(placeOrderRequestDTO);
        assertEquals(createMessage, message);
    }

    @Test
    void getOrderDetailsMessage() {
        String timestamp = TimeManager.getTimestampForOkx();
        String createMessage = createTradeMessageService.getOrderDetailsMessage(INST_ID, ORDER_ID, CLIENT_ORDER_ID, timestamp);
        String message = timestamp + Method.GET + ORDER_PATH + "?ordId=" +  ORDER_ID + "&clOrdId=" + CLIENT_ORDER_ID + "&instId=" + INST_ID;
        assertEquals(createMessage, message);
    }

    @Test
    void cancelOrderMessage() {
        CancelOrderRequestDTO cancelOrderRequestDTO = getCancelOrderRequestDTO();
        String timestamp = TimeManager.getTimestampForOkx();
        String createMessage = createTradeMessageService.cancelOrderMessage(cancelOrderRequestDTO, timestamp);
        String message = timestamp + Method.POST + CANCEL_ORDER_PATH + convertToJson(cancelOrderRequestDTO);
        assertEquals(createMessage, message);
    }

    @Test
    void amendOrderMessage() {
        AmendOrderRequestDTO amendOrderRequestDTO = getAmendOrderRequestDTO();
        String timestamp = TimeManager.getTimestampForOkx();
        String createMessage = createTradeMessageService.amendOrderMessage(amendOrderRequestDTO, timestamp);
        String message = timestamp + Method.POST + AMEN_ORDER_PATH + convertToJson(amendOrderRequestDTO);
        assertEquals(createMessage, message);
    }


    private PlaceOrderRequestDTO getPlaceOrderRequestDTO() {
        return PlaceOrderRequestDTO.builder()
                .instId(INST_ID)
                .px("1")
                .sz("1")
                .build();
    }

    private CancelOrderRequestDTO getCancelOrderRequestDTO() {
        return CancelOrderRequestDTO.builder()
                .instId(INST_ID)
                .clOrdId(CLIENT_ORDER_ID)
                .ordId(ORDER_ID)
                .build();
    }

    private AmendOrderRequestDTO getAmendOrderRequestDTO() {
        return AmendOrderRequestDTO.builder()
                .ordId(ORDER_ID)
                .clOrdId(CLIENT_ORDER_ID)
                .instId(INST_ID)
                .newPx(1)
                .reqId("1")
                .newSz("1")
                .build();
    }

    private <T> String convertToJson(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}