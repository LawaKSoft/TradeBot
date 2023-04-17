package by.lawaksoft.tradebot.service.manager;

import by.lawaksoft.tradebot.dto.manager.AlgoInstanceDto;
import org.springframework.stereotype.Service;

@Service
public interface AlgoManagerService {

    void run(AlgoInstanceDto algoInstanceDto);
}
