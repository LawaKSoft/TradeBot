package by.lawaksoft.tradebot.service.synchronize;

import org.springframework.stereotype.Service;

@Service
public interface SynchronizeService {

	void synchronizeMarketData();

	void stopSynchronizeMarketData();
}
