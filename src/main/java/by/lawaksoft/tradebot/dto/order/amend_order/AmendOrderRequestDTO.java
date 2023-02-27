package by.lawaksoft.tradebot.dto.order.amend_order;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AmendOrderRequestDTO {

    private String instId;
    private boolean cxlOnFail;
    private String ordId;
    private String clOrdId;
    private String reqId;
    private String newSz;
    private BigDecimal newPx;

    public AmendOrderRequestDTO(String instId, String ordId, BigDecimal newPx) {
        this.instId = instId;
        this.ordId = ordId;
        this.newPx = newPx;
    }
}
