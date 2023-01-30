package by.lawaksoft.tradebot.client;

import by.lawaksoft.tradebot.dto.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.dto.order.OrderResponseDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "${TRADE}",  url = "${TRADE_URL}")
public interface TradeClient {

    @GetMapping("${ORDER}")
    OrderDetailsResponseDTO getOrderDetails(@RequestParam("${INSTRUMENT_ID}") String instId,
                                            @RequestParam("${ORDER_ID}") String orderId,
                                            @RequestParam(value = "${CLIENT_ORDER_ID}", required = false) String clientOrderId,
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
