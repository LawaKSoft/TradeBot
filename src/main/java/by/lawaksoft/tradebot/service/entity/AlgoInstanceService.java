package by.lawaksoft.tradebot.service.entity;

import by.lawaksoft.tradebot.entity.AlgoInstance;
import by.lawaksoft.tradebot.entity.Order;

import java.util.List;
import java.util.Map;

public interface AlgoInstanceService {
    List<AlgoInstance> findAllByUserIdAndInstrumentId(long userId, String instrumentId);
}
