package by.lawaksoft.tradebot.service.synchronize.impl;

import by.lawaksoft.tradebot.service.synchronize.OkxWebsocketClient;
import by.lawaksoft.tradebot.service.synchronize.SynchronizeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SynchronizeServiceImpl implements SynchronizeService {

	private final OkxWebsocketClient okxWebsocketClient;

	public SynchronizeServiceImpl(OkxWebsocketClient okxWebsocketClient) {

		this.okxWebsocketClient = okxWebsocketClient;
	}

	@Override
	@Scheduled(fixedDelay = 60000)
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
