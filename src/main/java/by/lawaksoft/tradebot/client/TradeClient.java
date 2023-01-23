package by.lawaksoft.tradebot.client;

import by.lawaksoft.tradebot.dto.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.dto.order.OrderResponseDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = ClientNavigation.TRADE,  url = ClientNavigation.TRADE_URL)
public interface TradeClient {

    @GetMapping(ClientNavigation.ORDER)
    OrderDetailsResponseDTO getOrderDetails(@RequestParam(ClientNavigation.INSTRUMENT_ID) String instId,
                                            @RequestParam(ClientNavigation.ORDER_ID) String orderId,
                                            @RequestParam(value = ClientNavigation.CLIENT_ORDER_ID, required = false) String clientOrderId,
                                            @RequestHeader Map<String, String> header);

    @PostMapping(ClientNavigation.ORDER)
    OrderResponseDTO placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO,
                                     @RequestHeader Map<String, String> header);

    @PostMapping(ClientNavigation.CANCEL_ORDER)
    OrderResponseDTO cancelOrder(@RequestBody CancelOrderRequestDTO cancelOrderRequestDTO,
                                 @RequestHeader Map<String, String> header);

    @PostMapping(ClientNavigation.AMEND_ORDER)
    OrderResponseDTO amendOrder(@RequestBody AmendOrderRequestDTO amendOrderRequestDTO,
                                @RequestBody Map<String, String> header);
}
