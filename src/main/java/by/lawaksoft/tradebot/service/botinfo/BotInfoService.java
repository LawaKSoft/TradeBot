package by.lawaksoft.tradebot.service.botinfo;

import by.lawaksoft.tradebot.dto.botinfo.BotParametersDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface BotInfoService {

	BotParametersDto inputParameters(Map<String, String> botParameters);
}
