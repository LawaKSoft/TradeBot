package by.lawaksoft.tradebot.controller;

import by.lawaksoft.tradebot.dto.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderDetailsDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderResponseDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderResponseDTO;
import by.lawaksoft.tradebot.service.OrderDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderDtoService orderDtoService;

    @Autowired
    public OrderController(OrderDtoService orderDtoService) {
        this.orderDtoService = orderDtoService;
    }

    @PostMapping("/place")
    public ResponseEntity<GetOrderResponseDTO> placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO) {
        return new ResponseEntity<>(orderDtoService.placeOrder(placeOrderRequestDTO), OK);
    }

    @GetMapping("/place")
    public ResponseEntity<GetOrderDetailsDTO> getPlaceOrder(@RequestParam(value = "instId") String instrumentId,
                                                           @RequestParam(value = "ordId") String orderId,
                                                           @RequestParam(value = "clOrdId", required = false) String clientOrderId) {
        return new ResponseEntity<>(orderDtoService.getOrderDetails(instrumentId, orderId, clientOrderId), OK);
    }

    @DeleteMapping("/place")
    public ResponseEntity<HttpStatus> deletePlaceOrder(@RequestBody CancelOrderRequestDTO cancelOrderRequestDTO) {
        return null;
    }
}
