package by.lawaksoft.tradebot.controller.test;

import by.lawaksoft.tradebot.service.synchronize.SynchronizeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	private final SynchronizeService synchronizeService;

	public TestController(SynchronizeService synchronizeService) {

		this.synchronizeService = synchronizeService;
	}

	@GetMapping("/start-ws-okx")
	public ResponseEntity<?> startWSOkx () {

		synchronizeService.synchronizeMarketData();
		return ResponseEntity.ok().build();
	}

	@GetMapping("/stop-ws-okx")
	public ResponseEntity<?> stopWSOkx () {

		synchronizeService.stopSynchronizeMarketData();
		return ResponseEntity.ok().build();
	}
}
