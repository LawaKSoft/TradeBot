package by.lawaksoft.tradebot.client;

import org.springframework.util.StringUtils;

public interface ClientNavigation {

	String OKX_URL = "https://www.okx.com/api/v5";
	String PUBLIC = "public";
	String MARKET = "market";

	String MARKET_URL = OKX_URL + "/" + MARKET;
	String TICKER = "/ticker";
	String CANDLES = "/candles";

	String PUBLIC_URL = OKX_URL + "/" + PUBLIC;
	String INSTRUMENT = "/instruments";

	String INSTRUMENT_ID = "instId";
	String INSTRUMENT_TYPE = "instType";
	String INSTRUMENT_FAMILY = "instFamily";
	String BAR = "bar";
	String AFTER = "after";
	String BEFORE = "before";
	String LIMIT = "limit";
	String ULY = "uly";
}
