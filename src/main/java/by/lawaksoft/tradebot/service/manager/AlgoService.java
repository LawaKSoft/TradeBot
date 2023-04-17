package by.lawaksoft.tradebot.service.manager;

import by.lawaksoft.tradebot.dto.manager.AlgoInstanceDto;
import org.springframework.stereotype.Service;

@Service
public interface AlgoService {

    void execute(AlgoInstanceDto algoInstanceDto);
}
