package by.lawaksoft.tradebot.dto.order.cancel_order;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderRequestDTO {

    private String ordId;
    private String instId;
    private String clOrdId;

    public CancelOrderRequestDTO(String ordId, String instId) {
        this.ordId = ordId;
        this.instId = instId;
    }
}
