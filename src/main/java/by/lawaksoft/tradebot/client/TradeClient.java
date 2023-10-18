package by.lawaksoft.tradebot.client;

import by.lawaksoft.tradebot.dto.ResponseDTO;
import by.lawaksoft.tradebot.dto.order.OrderDataResponseDTO;
import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.dto.order.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.place_order.PlaceOrderRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static by.lawaksoft.tradebot.client.RequestParameters.*;

@FeignClient(name = "${TRADE}", url = "${TRADE_URL}")
public interface TradeClient {

    @GetMapping("${ORDER}")
    OrderDetailsResponseDTO getOrderDetailsByOrderId(@RequestParam(INSTRUMENT_ID) String instId,
                                                     @RequestParam(ORDER_ID) String orderId,
                                                     @RequestHeader Map<String, String> header);

    @GetMapping("${ORDER}")
    OrderDetailsResponseDTO getOrderDetailsByClientOrderId(@RequestParam(INSTRUMENT_ID) String instId,
                                                           @RequestParam(CLIENT_ORDER_ID) String clientOrderId,
                                                           @RequestHeader Map<String, String> header);

    @PostMapping("${ORDER}")
    ResponseDTO<OrderDataResponseDTO> placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO,
                                                 @RequestHeader Map<String, String> header);

    @PostMapping("${CANCEL_ORDER}")
    ResponseDTO<OrderDataResponseDTO> cancelOrder(@RequestBody CancelOrderRequestDTO cancelOrderRequestDTO,
                                                  @RequestHeader Map<String, String> header);

    @PostMapping("${AMEND_ORDER}")
    ResponseDTO<OrderDataResponseDTO> amendOrder(@RequestBody AmendOrderRequestDTO amendOrderRequestDTO,
                                                 @RequestHeader Map<String, String> header);

    @GetMapping("${ORDER_HISTORY_FOR_WEEK}")
    ResponseDTO<OrderDetailsResponseDTO> getOrderHistoryForWeekByInstrument(@RequestParam(INSTRUMENT_ID) String instId,
                                                                            @RequestParam(INST_TYPE) String instType,
                                                                            @RequestHeader Map<String, String> header);

    @GetMapping("${ORDER_HISTORY_FOR_WEEK}")
    ResponseDTO<OrderDetailsResponseDTO> getOrderHistoryForWeekByInstruments(@RequestParam(INST_TYPE) String instType,
                                                                             @RequestParam(INSTRUMENT_ID) String[] instIds,
                                                                             @RequestHeader Map<String, String> header);
}
