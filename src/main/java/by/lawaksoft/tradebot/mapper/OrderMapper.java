package by.lawaksoft.tradebot.mapper;

import by.lawaksoft.tradebot.dto.order.GetOrderDetailsDTO;
import by.lawaksoft.tradebot.dto.order.GetOrderResponseDTO;
import by.lawaksoft.tradebot.dto.order.OrderDetailsResponseDTO;
import by.lawaksoft.tradebot.dto.place_order.PlaceOrderRequestDTO;
import by.lawaksoft.tradebot.entity.Order;

public class OrderMapper {

    public static Order mapPlaceOrderRequestDTOToOrder(PlaceOrderRequestDTO placeOrderRequestDTO) {
        return Order.builder()
                .instrumentId(placeOrderRequestDTO.getInstId())
                .tradeMode(placeOrderRequestDTO.getTdMode())
                .clientOrderId(placeOrderRequestDTO.getClOrdId())
                .orderSide(placeOrderRequestDTO.getSide())
                .orderType(placeOrderRequestDTO.getOrdType())
                .price(Double.parseDouble(placeOrderRequestDTO.getPx()))
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
//                .lastFilledQuantity(order.getLastFilledQuantity())
//                .lastFilledPrice(order.getLastFilledPrice())
//                .lastTradeId(order.getLastTradeId())
//                .lastFilledTime(order.getLastFilledTime())
//                .averageFilledPrice(order.getAverageFilledPrice())
//                .state(order.getState())
//                .lever(order.getLever())
//                .feeCurrency(order.getFeeCurrency())
//                .feeAndRebate(order.getFeeAndRebate())
//                .category(order.getCategory())
//                .reduceOnly(order.isReduceOnly())
//                .canceledSource(order.getCanceledSource())
//                .cancelSourceReason(order.getCancelSourceReason())
//                .takeProfitOrderPrice(order.getTakeProfitOrderPrice())
//                .takeProfitTriggerPrice(order.getTakeProfitTriggerPrice())
//                .stopLossTriggerPrice(order.getStopLossTriggerPrice())
//                .stopLossOrderPrice(order.getStopLossOrderPrice())
//                .takeProfitTriggerPriceType(order.getTakeProfitTriggerPriceType())
//                .stopLossTriggerPriceType(order.getStopLossTriggerPriceType())
//                .quickMarginType(order.getQuickMarginType())
//                .unixTime(order.getUnixTime())
//                .creationTime(order.getCreationTime())
                .build();
    }
}
