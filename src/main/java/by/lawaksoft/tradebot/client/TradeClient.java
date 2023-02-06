package by.lawaksoft.tradebot.client;

import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.dto.order.OrderResponseDTO;
import by.lawaksoft.tradebot.dto.order.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.place_order.PlaceOrderRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "${TRADE}", url = "${TRADE_URL}")
public interface TradeClient {

    @GetMapping("${ORDER}")
    OrderDetailsResponseDTO getOrderDetailsByOrderId(@RequestParam(RequestParameters.INSTRUMENT_ID) String instId,
                                                     @RequestParam(RequestParameters.ORDER_ID) String orderId,
                                                     @RequestHeader Map<String, String> header);

    @GetMapping("${ORDER}")
    OrderDetailsResponseDTO getOrderDetailsByClientOrderId(@RequestParam(RequestParameters.INSTRUMENT_ID) String instId,
                                                           @RequestParam(value = RequestParameters.CLIENT_ORDER_ID) String clientOrderId,
                                                           @RequestHeader Map<String, String> header);

    @PostMapping("${ORDER}")
    OrderResponseDTO placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO,
                                @RequestHeader Map<String, String> header);

    @PostMapping("${CANCEL_ORDER}")
    OrderResponseDTO cancelOrder(@RequestBody CancelOrderRequestDTO cancelOrderRequestDTO,
                                 @RequestHeader Map<String, String> header);

    @PostMapping("${AMEND_ORDER}")
    OrderResponseDTO amendOrder(@RequestBody AmendOrderRequestDTO amendOrderRequestDTO,
                                @RequestHeader Map<String, String> header);
}
