package by.lawaksoft.tradebot.dto.account;

import by.lawaksoft.tradebot.dto.ResponseDTO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceResponseDTO<T> extends ResponseDTO<T> {

    private String adjEq;
    private String imr;
    private String isoEq;
    private String mgnRatio;
    private String mmr;
    private String notionalUsd;
    private String ordFroz;
    private String totalEq;
    private String uTime;
}
