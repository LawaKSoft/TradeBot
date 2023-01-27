package by.lawaksoft.tradebot.dto.cancel_order;

import lombok.*;
import org.springframework.stereotype.Component;

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
