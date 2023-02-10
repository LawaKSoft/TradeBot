package by.lawaksoft.tradebot.service.publicdata.impl;

import by.lawaksoft.tradebot.client.PublicClient;
import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentDto;
import by.lawaksoft.tradebot.dto.request.InstrumentsFilterDto;
import by.lawaksoft.tradebot.dto.response.instrument.FullInstrumentDto;
import by.lawaksoft.tradebot.dto.response.instrument.ResponseInstrumentDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublicDataServiceImplTest {

	@InjectMocks
	private PublicDataServiceImpl service;

	@Mock
	private PublicClient publicClient;

	@Test
	void getInstruments() {

		ResponseInstrumentDto mockResponseInstrumentDto = getMockResponseInstrumentDto();
		InstrumentsFilterDto mockFilterDto = getMockFilterDto();

		when(publicClient.getInstruments(anyString(), any(), any(), any())).thenReturn(mockResponseInstrumentDto);

		List<InstrumentDto> instruments = service.getInstruments(mockFilterDto);

		assertEquals(1, instruments.size());
		verify(publicClient, times(1)).getInstruments(anyString(), any(), any(), any());
	}

	private ResponseInstrumentDto getMockResponseInstrumentDto() {

		return ResponseInstrumentDto.builder()
				.code("0")
				.message("")
				.instrumentDtos(List.of(FullInstrumentDto.builder()
								.alias("")
								.baseCurrency("BTC")
								.currencyCategory("1")
								.contractMultiplier("")
								.contractTypeLinear("")
								.contractValue("")
								.contractValueCurrency("")
								.expiryTime("")
								.instrumentFamily("")
								.instrumentId("BTC-USDT")
								.instrumentType("SPOT")
								.maxLeverage("10")
								.listingTime("1548133413000")
								.lotSize("0.00000001")
								.maxIceBergSize("9999999999")
								.maxLimitSize("9999999999")
								.maxMarketSize("1000000")
								.maxStopSize("1000000")
								.maxTriggerSize("9999999999")
								.maxTwapSize("9999999999")
								.minOrderSize("0.00001")
								.optionType("")
								.quoteCurrency("USDT")
								.settlementAndMarginCurrency("")
								.instrumentState("live")
								.tickSize("")
								.tickSize("0.1")
								.underlying("")
						.build()))
				.build();
	}

	private InstrumentsFilterDto getMockFilterDto() {

		return InstrumentsFilterDto.builder()
				.instType("SPOT")
				.uly(null)
				.instFamily(null)
				.instId("BTC-USDT")
				.build();
	}
}