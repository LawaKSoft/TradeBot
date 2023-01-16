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
	String INSTRUMENT = "/instrument";

	String INSTRUMENT_ID = "instId";
}
