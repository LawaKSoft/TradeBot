package by.lawaksoft.tradebot.mapper;

import by.lawaksoft.tradebot.document.Order;
import by.lawaksoft.tradebot.dto.order.GetOrderDetailsDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderResponseDTO;
import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.dto.order.place_order.PlaceOrderRequestDTO;

public class OrderMapper {

    private OrderMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Order mapPlaceOrderRequestDTOToOrder(PlaceOrderRequestDTO placeOrderRequestDTO) {
        return Order.builder()
                .instrumentId(placeOrderRequestDTO.getInstId())
                .tradeMode(placeOrderRequestDTO.getTdMode())
                .clientOrderId(placeOrderRequestDTO.getClOrdId())
                .orderSide(placeOrderRequestDTO.getSide())
                .orderType(placeOrderRequestDTO.getOrdType())
                .price(placeOrderRequestDTO.getPx())
                .quantityToBuyOrSell(Double.parseDouble(placeOrderRequestDTO.getSz()))
                .tag(placeOrderRequestDTO.getTag())
                .positionSide(placeOrderRequestDTO.getPosSide())
                .reduceOnly(placeOrderRequestDTO.isReduceOnly())
                .quantityUnitService(placeOrderRequestDTO.getTgtCcy())
                .banAmend(placeOrderRequestDTO.isBanAmend())
                .takeProfitOrderPrice(placeOrderRequestDTO.getTpOrdPx())
                .takeProfitTriggerPrice(placeOrderRequestDTO.getTpTriggerPx())
                .stopLossTriggerPrice(placeOrderRequestDTO.getSlTriggerPx())
                .stopLossOrderPrice(placeOrderRequestDTO.getSlOrdPx())
                .takeProfitTriggerPriceType(placeOrderRequestDTO.getTpTriggerPxType())
                .stopLossTriggerPriceType(placeOrderRequestDTO.getSlTriggerPxType())
                .build();
    }


    public static GetOrderResponseDTO mapOrderToGetOrderResponseDTO(Order order) {
        return GetOrderResponseDTO.builder()
                .id(order.getId())
                .orderId(order.getOrderId())
                .clientOrderId(order.getClientOrderId())
                .instrumentId(order.getInstrumentId())
                .tag(order.getTag())
                .build();
    }

    public static GetOrderDetailsDTO mapOrderToGetOrderDetailsDTO(Order order) {
        return GetOrderDetailsDTO.builder()
                .id(order.getId())
                .instrumentType(order.getInstrumentType())
                .instrumentId(order.getInstrumentId())
                .clientOrderId(order.getClientOrderId())
                .orderSide(order.getOrderSide())
                .orderId(order.getOrderId())
                .profitAndLoss(order.getProfitAndLoss())
                .orderType(order.getOrderType())
                .price(order.getPrice())
                .quantityToBuyOrSell(order.getQuantityToBuyOrSell())
                .tag(order.getTag())
                .positionSide(order.getPositionSide())
                .reduceOnly(order.isReduceOnly())
                .quantityUnitService(order.getQuantityUnitService())
                .banAmend(order.isBanAmend())
                .accumulatedFillQuantity(order.getAccumulatedFillQuantity())
                .lastFilledQuantity(order.getLastFilledQuantity())
                .lastFilledPrice(order.getLastFilledPrice())
                .lastTradeId(order.getLastTradeId())
                .lastFilledTime(order.getLastFilledTime())
                .averageFilledPrice(order.getAverageFilledPrice())
                .state(order.getState())
                .source(order.getSource())
                .rebate(order.getRebate())
                .feeCurrency(order.getFeeCurrency())
                .feeAndRebate(order.getFeeAndRebate())
                .category(order.getCategory())
                .reduceOnly(order.isReduceOnly())
                .canceledSource(order.getCanceledSource())
                .cancelSourceReason(order.getCancelSourceReason())
                .takeProfitOrderPrice(order.getTakeProfitOrderPrice())
                .takeProfitTriggerPrice(order.getTakeProfitTriggerPrice())
                .stopLossTriggerPrice(order.getStopLossTriggerPrice())
                .stopLossOrderPrice(order.getStopLossOrderPrice())
                .takeProfitTriggerPriceType(order.getTakeProfitTriggerPriceType())
                .stopLossTriggerPriceType(order.getStopLossTriggerPriceType())
                .unixTime(order.getUnixTime())
                .creationTime(order.getCreationTime())
                .build();
    }

    public static Order mapOrderDetailsResponseDTOToOrder(OrderDetailsResponseDTO order) {
        return Order.builder()
                .instrumentType(order.getInstType())
                .instrumentId(order.getInstId())
                .tradeMode(order.getTdMode())
                .clientOrderId(order.getClOrdId())
                .orderSide(order.getSide())
                .orderId(order.getOrdId())
                .profitAndLoss(order.getPnl())
                .orderType(order.getOrdType())
                .price(order.getPx())
                .quantityToBuyOrSell(order.getSz())
                .tag(order.getTag())
                .positionSide(order.getPosSide())
                .reduceOnly(order.isReduceOnly())
                .quantityUnitService(order.getTgtCcy())
                .accumulatedFillQuantity(order.getFillPx())
                .lastFilledQuantity(order.getFillSz())
                .lastFilledPrice(order.getFillPx())
                .lastTradeId(order.getTradeId())
                .lastFilledTime(order.getFillTime())
                .averageFilledPrice(order.getAvgPx())
                .state(order.getState())
                .source(order.getSource())
                .rebateCurrency(order.getRebateCcy())
                .rebate(order.getRebate())
                .feeCurrency(order.getFeeCcy())
                .feeAndRebate(order.getFee())
                .category(order.getCategory())
                .canceledSource(order.getCancelSource())
                .cancelSourceReason(order.getCancelSourceReason())
                .takeProfitOrderPrice(order.getTpOrdPx())
                .takeProfitTriggerPrice(order.getTpTriggerPx())
                .takeProfitTriggerPriceType(order.getTpTriggerPxType())
                .stopLossTriggerPrice(order.getSlTriggerPx())
                .stopLossOrderPrice(order.getSlOrdPx())
                .stopLossTriggerPriceType(order.getSlTriggerPxType())
                .unixTime(order.getUTime())
                .creationTime(order.getCTime())
                .build();
    }
}
