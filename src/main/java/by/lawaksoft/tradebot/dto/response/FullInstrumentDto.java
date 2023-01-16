package by.lawaksoft.tradebot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FullInstrumentDto {

	private final String intrumentType;//instType	String	Instrument type
	private final String instrumentId;//instId	String	Instrument ID, e.g. BTC-USD-SWAP
	private final String underlying;//uly	String	Underlying, e.g. BTC-USD Only applicable to FUTURES/SWAP/OPTION
	private final String instrumentFamily;//instFamily	String	Instrument family, e.g. BTC-USD 	Only applicable to FUTURES/SWAP/OPTION
	private final String currencyCategory;//category	String	Currency category. Note: this parameter is already deprecated
	private final String baseCurrency;//baseCcy	String	Base currency, e.g. BTC inBTC-USDT 	Only applicable to SPOT/MARGIN
	private final String quoteCurrency;//quoteCcy	String	Quote currency, e.g. USDT in BTC-USDT 	Only applicable to SPOT/MARGIN
	private final String settlementAndMarginCurrency;//settleCcy	String	Settlement and margin currency, e.g. BTC 	Only applicable to FUTURES/SWAP/OPTION
	private final String contractValue;//ctVal	String	Contract value 	Only applicable to FUTURES/SWAP/OPTION
	private final String contractMultiplier;//ctMult	String	Contract multiplier 	Only applicable to FUTURES/SWAP/OPTION
	private final String contractValueCurrency;//ctValCcy	String	Contract value currency 	Only applicable to FUTURES/SWAP/OPTION
	private final String optionType;//optType	String	Option type, C: Call P: put 	Only applicable to OPTION
	private final String strikePrice;//stk	String	Strike price 	Only applicable to OPTION
	private final String listingTime;//listTime	String	Listing time, Unix timestamp format in milliseconds, e.g. 1597026383085
	private final String expiryTime;//expTime	String	Expiry time, Unix timestamp format in milliseconds, e.g. 1597026383085 	Only applicable to FUTURES/OPTION
	private final String maxLeverage;//lever	String	Max Leverage, 	Not applicable to SPOT, OPTION
	private final String tickSize;//tickSz	String	Tick size, e.g. 0.0001
	private final String lotSize;//lotSz	String	Lot size, e.g. BTC-USDT-SWAP: 1
	private final String minOrderSize;//minSz	String	Minimum order size
	private final String contractTypeLinear;//ctType	String	Contract type linear: linear contract inverse: inverse contract 	Only applicable to FUTURES/SWAP
	private final String alias;//alias	String	Alias 	this_week next_week quarter next_quarter 	Only applicable to FUTURES
	private final String instrumentState;//state	String	Instrument status 	live suspend preopen test: Test pairs, can't be traded
	private final String maxLimitSize;//maxLmtSz	String	The maximum order quantity of the contract or spot limit order
	private final String maxMarketSize;//maxMktSz	String	The maximum order quantity of the contract or spot market order
	private final String maxTwapSize;//maxTwapSz	String	The maximum order quantity of the contract or spot twap order
	private final String maxIceBergSize;//maxIcebergSz	String	The maximum order quantity of the contract or spot iceBerg order
	private final String maxTriggerSize;//maxTriggerSz	String	The maximum order quantity of the contract or spot trigger order
	private final String maxStopSize;//maxStopSz	String	The maximum order quantity of the contract or spot stop order

	public FullInstrumentDto(@JsonProperty("instType") String intrumentType, @JsonProperty("instId") String instrumentId, @JsonProperty("uly") String underlying,
			@JsonProperty("instFamily") String instrumentFamily, @JsonProperty("category") String currencyCategory, @JsonProperty("baseCcy") String baseCurrency,
			@JsonProperty("quoteCcy") String quoteCurrency, @JsonProperty("settleCcy") String settlementAndMarginCurrency, @JsonProperty("ctVal") String contractValue,
			@JsonProperty("ctMult") String contractMultiplier, @JsonProperty("ctValCcy") String contractValueCurrency, @JsonProperty("optType") String optionType,
			@JsonProperty("stk") String strikePrice, @JsonProperty("listTime") String listingTime, @JsonProperty("expTime") String expiryTime,
			@JsonProperty("lever") String maxLeverage, @JsonProperty("tickSz") String tickSize, @JsonProperty("lotSz") String lotSize, @JsonProperty("minSz") String minOrderSize,
			@JsonProperty("ctType") String contractTypeLinear, @JsonProperty("alias") String alias, @JsonProperty("state") String instrumentState,
			@JsonProperty("maxLmtSz") String maxLimitSize, @JsonProperty("maxMktSz") String maxMarketSize, @JsonProperty("maxTwapSz") String maxTwapSize,
			@JsonProperty("maxIcebergSz") String maxIceBergSize, @JsonProperty("maxTriggerSz") String maxTriggerSize, @JsonProperty("maxStopSz") String maxStopSize) {

		this.intrumentType = intrumentType;
		this.instrumentId = instrumentId;
		this.underlying = underlying;
		this.instrumentFamily = instrumentFamily;
		this.currencyCategory = currencyCategory;
		this.baseCurrency = baseCurrency;
		this.quoteCurrency = quoteCurrency;
		this.settlementAndMarginCurrency = settlementAndMarginCurrency;
		this.contractValue = contractValue;
		this.contractMultiplier = contractMultiplier;
		this.contractValueCurrency = contractValueCurrency;
		this.optionType = optionType;
		this.strikePrice = strikePrice;
		this.listingTime = listingTime;
		this.expiryTime = expiryTime;
		this.maxLeverage = maxLeverage;
		this.tickSize = tickSize;
		this.lotSize = lotSize;
		this.minOrderSize = minOrderSize;
		this.contractTypeLinear = contractTypeLinear;
		this.alias = alias;
		this.instrumentState = instrumentState;
		this.maxLimitSize = maxLimitSize;
		this.maxMarketSize = maxMarketSize;
		this.maxTwapSize = maxTwapSize;
		this.maxIceBergSize = maxIceBergSize;
		this.maxTriggerSize = maxTriggerSize;
		this.maxStopSize = maxStopSize;
	}

	public String getIntrumentType() {

		return intrumentType;
	}

	public String getInstrumentId() {

		return instrumentId;
	}

	public String getUnderlying() {

		return underlying;
	}

	public String getInstrumentFamily() {

		return instrumentFamily;
	}

	public String getCurrencyCategory() {

		return currencyCategory;
	}

	public String getBaseCurrency() {

		return baseCurrency;
	}

	public String getQuoteCurrency() {

		return quoteCurrency;
	}

	public String getSettlementAndMarginCurrency() {

		return settlementAndMarginCurrency;
	}

	public String getContractValue() {

		return contractValue;
	}

	public String getContractMultiplier() {

		return contractMultiplier;
	}

	public String getContractValueCurrency() {

		return contractValueCurrency;
	}

	public String getOptionType() {

		return optionType;
	}

	public String getStrikePrice() {

		return strikePrice;
	}

	public String getListingTime() {

		return listingTime;
	}

	public String getExpiryTime() {

		return expiryTime;
	}

	public String getMaxLeverage() {

		return maxLeverage;
	}

	public String getTickSize() {

		return tickSize;
	}

	public String getLotSize() {

		return lotSize;
	}

	public String getMinOrderSize() {

		return minOrderSize;
	}

	public String getContractTypeLinear() {

		return contractTypeLinear;
	}

	public String getAlias() {

		return alias;
	}

	public String getInstrumentState() {

		return instrumentState;
	}

	public String getMaxLimitSize() {

		return maxLimitSize;
	}

	public String getMaxMarketSize() {

		return maxMarketSize;
	}

	public String getMaxTwapSize() {

		return maxTwapSize;
	}

	public String getMaxIceBergSize() {

		return maxIceBergSize;
	}

	public String getMaxTriggerSize() {

		return maxTriggerSize;
	}

	public String getMaxStopSize() {

		return maxStopSize;
	}
}
