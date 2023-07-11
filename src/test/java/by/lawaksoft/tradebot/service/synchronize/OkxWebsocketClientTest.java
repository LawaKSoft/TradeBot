package by.lawaksoft.tradebot.service.synchronize;

import by.lawaksoft.tradebot.document.Candlestick;
import by.lawaksoft.tradebot.entity.Instrument;
import by.lawaksoft.tradebot.exception.JsonMapperException;
import by.lawaksoft.tradebot.repository.InstrumentRepository;
import by.lawaksoft.tradebot.repository.mongo.CandlestickRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OkxWebsocketClientTest {

	@Mock
	private InstrumentRepository instrumentRepository;
	@Mock
	private CandlestickRepository candlestickRepository;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private OkxWebsocketClient okxWebsocketClient;

	@BeforeEach
	void beforeAll() {

		okxWebsocketClient = new OkxWebsocketClient(instrumentRepository, candlestickRepository, objectMapper);
	}

	@Test
	void onOpenInstrumentsNotFound() {

		ServerHandshake serverHandshake = mock(ServerHandshake.class);

		when(instrumentRepository.findAll()).thenReturn(List.of());

		okxWebsocketClient.onOpen(serverHandshake);

		verify(instrumentRepository, times(1)).findAll();
	}

	@Test
	void onOpenMapperFailed() {

		ServerHandshake serverHandshake = mock(ServerHandshake.class);

		when(instrumentRepository.findAll()).thenReturn(List.of());

		okxWebsocketClient.onOpen(serverHandshake);

		verify(instrumentRepository, times(1)).findAll();
	}

	@Test
	void onMessage() {

		String message = "{\"arg\":{\"channel\":\"candle3M\",\"instId\":\"BTC-USDT\"},\"data\":[[\"1688140800000\",\"30068.2\",\"31550\",\"29705.2\",\"30392.3\",\"74836.89111824\",\"2286851274.150883449\",\"2286851274.150883449\",\"0\"]]}";

		when(instrumentRepository.findByInstrumentId(anyString())).thenReturn(Optional.of(getMockInstrument()));
		when(candlestickRepository.save(any())).thenReturn(mock(Candlestick.class));

		okxWebsocketClient.onMessage(message);

		verify(instrumentRepository, times(1)).findByInstrumentId(anyString());
		verify(candlestickRepository, times(1)).save(any());
	}

	@Test
	void onMessageEvent() {

		String message = "{\"event\":\"subscribe\",\"arg\":{\"channel\":\"candle3M\",\"instId\":\"BTC-USDT\"}}";

		okxWebsocketClient.onMessage(message);

		verify(instrumentRepository, never()).findByInstrumentId(anyString());
		verify(candlestickRepository, never()).save(any());
	}

	@Test
	void onMessageInstrumentNotFound() {

		String message = "{\"arg\":{\"channel\":\"candle3M\",\"instId\":\"BTC-USDT\"},\"data\":[[\"1688140800000\",\"30068.2\",\"31550\",\"29705.2\",\"30392.3\",\"74836.89111824\",\"2286851274.150883449\",\"2286851274.150883449\",\"0\"]]}";

		when(instrumentRepository.findByInstrumentId(anyString())).thenThrow(EntityNotFoundException.class);

		assertThrows(EntityNotFoundException.class, () -> okxWebsocketClient.onMessage(message));

		verify(instrumentRepository, times(1)).findByInstrumentId(anyString());
		verify(candlestickRepository, never()).save(any());
	}

	@Test
	void onMessageMapperFailed() {

		String message = "";

		assertThrows(JsonMapperException.class, () -> okxWebsocketClient.onMessage(message));

		verify(instrumentRepository, never()).findByInstrumentId(anyString());
		verify(candlestickRepository, never()).save(any());
	}

	private Instrument getMockInstrument() {

		return Instrument.builder()
				.id(1L)
				.instrumentId("BTC-USDT")
				.build();
	}
}