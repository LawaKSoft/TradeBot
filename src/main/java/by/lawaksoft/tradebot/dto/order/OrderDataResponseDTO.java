package by.lawaksoft.tradebot.dto.order;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDataResponseDTO {

    private String clOrdId;
    private String ordId;
    private String tag;
    private String reqId;
    private int sCode;
    private String sMsg;

    public OrderDataResponseDTO(String clOrdId, String ordId, String tag, int sCode, String sMsg) {
        this.clOrdId = clOrdId;
        this.ordId = ordId;
        this.tag = tag;
        this.sCode = sCode;
        this.sMsg = sMsg;
    }

    public OrderDataResponseDTO(String clOrdId, String ordId, int sCode, String sMsg) {
        this.clOrdId = clOrdId;
        this.ordId = ordId;
        this.sCode = sCode;
        this.sMsg = sMsg;
    }
}
