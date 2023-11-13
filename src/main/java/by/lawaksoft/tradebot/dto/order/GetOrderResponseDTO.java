package by.lawaksoft.tradebot.dto.order;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetOrderResponseDTO {

    private String id;
    private String instrumentId;
    private String clientOrderId;
    private String orderId;
    private String tag;
}
