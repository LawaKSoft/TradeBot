package by.lawaksoft.tradebot.controller.trade;

import by.lawaksoft.tradebot.dto.amend_order.AmendOrderRequestDTO;
import by.lawaksoft.tradebot.dto.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderDetailsDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderResponseDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.service.api.trade.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${TRADE}")
public class TradeController {

    private final TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping("${PLACE}")
    public ResponseEntity<GetOrderResponseDTO> placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO) {
        return ResponseEntity.ok(tradeService.placeOrder(placeOrderRequestDTO));
    }

    @GetMapping("${ORDER}")
    public ResponseEntity<GetOrderDetailsDTO> getOrderDetails(@RequestParam String instrumentId,
                                                              @RequestParam(required = false) String orderId,
                                                              @RequestParam(required = false) String clientOrderId) {
        return ResponseEntity.ok(tradeService.getOrderDetails(instrumentId, orderId, clientOrderId));
    }

    @PostMapping("${CANCEL_ORDER}")
    public ResponseEntity<GetOrderResponseDTO> cancelPlaceOrder(@RequestBody CancelOrderRequestDTO cancelOrderRequestDTO) {
        return ResponseEntity.ok(tradeService.cancelOrder(cancelOrderRequestDTO));
    }

    @PutMapping("${AMEND_ORDER}")
    public ResponseEntity<GetOrderResponseDTO> amendOrder(@RequestBody AmendOrderRequestDTO amendOrderRequestDTO) {
        return ResponseEntity.ok(tradeService.amendOrder(amendOrderRequestDTO));
    }
}
