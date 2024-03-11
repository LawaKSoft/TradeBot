package by.lawaksoft.tradebot.service.clientinfo;

import by.lawaksoft.tradebot.dto.clientinfo.ClientParametersDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ClientInfoService {

	ClientParametersDto inputParameters(Map<String, String> clientParameters);
}
