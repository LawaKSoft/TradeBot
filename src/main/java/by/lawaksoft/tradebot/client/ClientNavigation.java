package by.lawaksoft.tradebot.client;

public interface ClientNavigation {


    String OKX_URL = "https://www.okx.com/api/v5";
    String PUBLIC = "public";
    String MARKET = "market";
    String TRADE = "trade";

    String MARKET_URL = OKX_URL + "/" + MARKET;
    String TICKER = "/ticker";
    String CANDLES = "/candles";

    String PUBLIC_URL = OKX_URL + "/" + PUBLIC;
    String INSTRUMENT = "/instrument";

    String TRADE_URL = OKX_URL + "/" + TRADE;
    String ORDER = "/order";
    String CANCEL_ORDER = "/cancel-order";
    String AMEND_ORDER = "/amend-order";

    String INSTRUMENT_ID = "instId";
    String ORDER_ID = "ordId";
    String CLIENT_ORDER_ID = "clOrdId";
}
