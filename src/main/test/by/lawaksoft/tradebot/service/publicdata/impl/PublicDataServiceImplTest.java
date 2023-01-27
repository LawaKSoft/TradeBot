package by.lawaksoft.tradebot.service.publicdata.impl;

import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentDto;
import by.lawaksoft.tradebot.dto.request.InstrumentsFilterDto;
import by.lawaksoft.tradebot.service.publicdata.PublicDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PublicDataServiceImplTest {

	@Autowired
	private PublicDataService publicDataService;

	@Test
	void getInstruments() {

		InstrumentsFilterDto filterDto = new InstrumentsFilterDto("SPOT", null, null, "BTC-USDT");
		List<InstrumentDto> instruments = publicDataService.getInstruments(filterDto);

		assertEquals(instruments.size(), 1);
		assertEquals(instruments.get(0).getInstrumentType(), "SPOT");
	}
}