package by.lawaksoft.tradebot.dto.order;

import lombok.Builder;

@Builder
public class    GetOrderResponseDTO {

    private long id;
    private String instrumentId;
    private String clientOrderId;
    private String orderId;
    private String tag;
}
