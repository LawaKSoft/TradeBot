package by.lawaksoft.tradebot.dto.response.instrument;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FullInstrumentDto {

	@JsonProperty("instType")
	private final String instrumentType;
	@JsonProperty("instId")
	private final String instrumentId;
	@JsonProperty("uly")
	private final String underlying;
	@JsonProperty("instFamily")
	private final String instrumentFamily;
	@JsonProperty("category")
	private final String currencyCategory;
	@JsonProperty("baseCcy")
	private final String baseCurrency;
	@JsonProperty("quoteCcy")
	private final String quoteCurrency;
	@JsonProperty("settleCcy")
	private final String settlementAndMarginCurrency;
	@JsonProperty("ctVal")
	private final String contractValue;
	@JsonProperty("ctMult")
	private final String contractMultiplier;
	@JsonProperty("ctValCcy")
	private final String contractValueCurrency;
	@JsonProperty("optType")
	private final String optionType;
	@JsonProperty("stk")
	private final String strikePrice;
	@JsonProperty("listTime")
	private final String listingTime;
	@JsonProperty("expTime")
	private final String expiryTime;
	@JsonProperty("lever")
	private final String maxLeverage;
	@JsonProperty("tickSz")
	private final String tickSize;
	@JsonProperty("lotSz")
	private final String lotSize;
	@JsonProperty("minSz")
	private final String minOrderSize;
	@JsonProperty("ctType")
	private final String contractTypeLinear;
	@JsonProperty("alias")
	private final String alias;
	@JsonProperty("state")
	private final String instrumentState;
	@JsonProperty("maxLmtSz")
	private final String maxLimitSize;
	@JsonProperty("maxMktSz")
	private final String maxMarketSize;
	@JsonProperty("maxTwapSz")
	private final String maxTwapSize;
	@JsonProperty("maxIcebergSz")
	private final String maxIceBergSize;
	@JsonProperty("maxTriggerSz")
	private final String maxTriggerSize;
	@JsonProperty("maxStopSz")
	private final String maxStopSize;
}
