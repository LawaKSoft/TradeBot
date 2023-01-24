package by.lawaksoft.tradebot.dto.amend_order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AmendOrderRequestDTO {

    private String instId;
    private boolean cxlOnFail;
    private String ordId;
    private String clOrdId;
    private String reqId;
    private String newSz;
    private double newPx;

    public AmendOrderRequestDTO(String instId, String ordId, double newPx) {
        this.instId = instId;
        this.ordId = ordId;
        this.newPx = newPx;
    }

    public AmendOrderRequestDTO(String instId, String ordId, String newSz) {
        this.instId = instId;
        this.ordId = ordId;
        this.newSz = newSz;
    }
}
