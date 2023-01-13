package by.lawaksoft.tradebot.dto.cancel_order;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
public class CancelOrderRequestDTO {

    private String ordId;
    private String instId;
    private String clOrdId;
}
