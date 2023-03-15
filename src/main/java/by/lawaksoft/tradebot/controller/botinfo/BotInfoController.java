package by.lawaksoft.tradebot.controller.botinfo;

import by.lawaksoft.tradebot.dto.botinfo.BotParametersDto;
import by.lawaksoft.tradebot.service.botinfo.BotInfoService;
import by.lawaksoft.tradebot.dto.botinfo.SimpleBotParametersDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/bot-info")
public class BotInfoController {

	private final BotInfoService botInfoService;

	public BotInfoController(BotInfoService botInfoService) {

		this.botInfoService = botInfoService;
	}

	@PostMapping("/input")
	public ResponseEntity<BotParametersDto> inputParameters(@RequestBody Map<String, String> botParameters) {

		BotParametersDto botParametersDto = botInfoService.inputParameters(botParameters);
		return ResponseEntity.ok(botParametersDto);
	}
}
