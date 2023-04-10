package by.lawaksoft.tradebot.controller.clientinfo;

import by.lawaksoft.tradebot.dto.clientinfo.ClientParametersDto;
import by.lawaksoft.tradebot.service.clientinfo.ClientInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/client-info")
public class ClientInfoController {

	private final ClientInfoService clientInfoService;

	public ClientInfoController(ClientInfoService clientInfoService) {

		this.clientInfoService = clientInfoService;
	}

	@PostMapping("/input")
	public ResponseEntity<ClientParametersDto> inputClientParameters(Map<String, String> clientParameters) {

		ClientParametersDto clientParametersDto = clientInfoService.inputParameters(clientParameters);
		return ResponseEntity.ok(clientParametersDto);
	}
}
