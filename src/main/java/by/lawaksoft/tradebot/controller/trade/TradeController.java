package by.lawaksoft.tradebot.controller.trade;

import by.lawaksoft.tradebot.dto.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderDetailsDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderResponseDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.service.api.trade.ApiTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/trade/order")
public class TradeController {

    private final ApiTradeService apiTradeService;

    @Autowired
    public TradeController(ApiTradeService apiTradeService) {
        this.apiTradeService = apiTradeService;
    }

    @PostMapping("/place")
    public ResponseEntity<GetOrderResponseDTO> placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO) {
        return ResponseEntity.ok(apiTradeService.placeOrder(placeOrderRequestDTO));
    }

    @GetMapping("/details")
    public ResponseEntity<GetOrderDetailsDTO> getOrderDetails(@RequestParam String instrumentId,
                                                              @RequestParam(required = false) String orderId,
                                                              @RequestParam(required = false) String clientOrderId) {
        return ResponseEntity.ok(apiTradeService.getOrderDetails(instrumentId, orderId, clientOrderId));
    }

    @PostMapping("/cancel")
    public ResponseEntity<GetOrderResponseDTO> cancelPlaceOrder(@RequestBody CancelOrderRequestDTO cancelOrderRequestDTO) {
        return ResponseEntity.ok(apiTradeService.cancelOrder(cancelOrderRequestDTO));
    }

    @PutMapping("/amend")
    public ResponseEntity<GetOrderResponseDTO> amendOrder(@RequestBody AmendOrderRequestDTO amendOrderRequestDTO) {
        return ResponseEntity.ok(apiTradeService.amendOrder(amendOrderRequestDTO));
    }
}
