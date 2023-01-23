package by.lawaksoft.tradebot.controller;

import by.lawaksoft.tradebot.client.TradeClient;
import by.lawaksoft.tradebot.dto.cancel_order.CancelOrderRequestDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderDetailsDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderResponseDTO;
import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.service.trade.TradeService;
import by.lawaksoft.tradebot.util.Encoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping(Navigation.ORDER)
public class OrderController {

    private final TradeService tradeService;

    @Autowired
    public OrderController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @Autowired
    private TradeClient tradeClient;

    @PostMapping(Navigation.PLACE)
    public ResponseEntity<GetOrderResponseDTO> placeOrder(@RequestBody PlaceOrderRequestDTO placeOrderRequestDTO) {
        return ResponseEntity.ok(tradeService.placeOrder(placeOrderRequestDTO));
    }

    @GetMapping(Navigation.PLACE)
    public ResponseEntity<GetOrderDetailsDTO> getOrderDetails(@RequestParam String instrumentId,
                                                            @RequestParam(required = false) String orderId,
                                                            @RequestParam(required = false) String clientOrderId) {
        return ResponseEntity.ok(tradeService.getOrderDetails(instrumentId, orderId, clientOrderId));
    }

    @DeleteMapping(Navigation.PLACE)
    public ResponseEntity<GetOrderResponseDTO> deletePlaceOrder(@RequestBody CancelOrderRequestDTO cancelOrderRequestDTO) {
        return ResponseEntity.ok(tradeService.cancelOrder(cancelOrderRequestDTO));
    }
}
