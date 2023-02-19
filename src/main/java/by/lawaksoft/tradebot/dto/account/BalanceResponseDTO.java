package by.lawaksoft.tradebot.dto.account;

import by.lawaksoft.tradebot.dto.ResponseDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceResponseDTO {

    private String adjEq;
    private List<BalanceDataResponseDTO> details;
    private String imr;
    private String isoEq;
    private String mgnRatio;
    private String mmr;
    private String notionalUsd;
    private String ordFroz;
    private String totalEq;
    private String uTime;
}
