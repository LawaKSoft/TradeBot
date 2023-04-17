package by.lawaksoft.tradebot.service.manager;

import by.lawaksoft.tradebot.dto.AlgoInstanceDto;
import by.lawaksoft.tradebot.entity.AlgoInstance;
import by.lawaksoft.tradebot.repository.AlgoInstanceRepository;

public interface AlgoService {

    void execute(AlgoInstanceDto algoInstanceDto);
}
