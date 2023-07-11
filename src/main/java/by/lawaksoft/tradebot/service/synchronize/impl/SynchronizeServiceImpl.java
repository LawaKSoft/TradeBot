package by.lawaksoft.tradebot.service.synchronize.impl;

import by.lawaksoft.tradebot.service.synchronize.OkxWebsocketClient;
import by.lawaksoft.tradebot.service.synchronize.SynchronizeService;
import org.springframework.stereotype.Component;

@Component
public class SynchronizeServiceImpl implements SynchronizeService {

	private final OkxWebsocketClient okxWebsocketClient;

	public SynchronizeServiceImpl(OkxWebsocketClient okxWebsocketClient) {

		this.okxWebsocketClient = okxWebsocketClient;
	}

	@Override
	public void synchronizeMarketData() {

		okxWebsocketClient.connect();
	}

	@Override
	public void stopSynchronizeMarketData() {

		if (okxWebsocketClient.isOpen()) {
			okxWebsocketClient.close();
		}
	}
}
