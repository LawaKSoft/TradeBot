package by.lawaksoft.tradebot.dto.account;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceDataResponseDTO {

    private String availBal;
    private String availEq;
    private String cashBal;
    private String ccy;
    private String crossLiab;
    private String disEq;
    private String eq;
    private String eqUsd;
    private String frozenBal;
    private String interest;
    private String isoEq;
    private String isoLiab;
    private String isoUpl;
    private String liab;
    private String maxLoan;
    private String mgnRatio;
    private String notionalLever;
    private String ordFrozen;
    private String twap;
    private String uTime;
    private String upl;
    private String uplLiab;
    private String stgyEq;
    private String spotInUseAmt;
}
