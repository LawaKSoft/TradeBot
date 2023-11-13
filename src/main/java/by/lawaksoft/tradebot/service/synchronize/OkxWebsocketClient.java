package by.lawaksoft.tradebot.service.synchronize;

import by.lawaksoft.tradebot.dto.model.market.CandlestickDto;
import by.lawaksoft.tradebot.document.Candlestick;
import by.lawaksoft.tradebot.entity.Instrument;
import by.lawaksoft.tradebot.exception.JsonMapperException;
import by.lawaksoft.tradebot.mapper.DtoMapper;
import by.lawaksoft.tradebot.mapper.DocumentMapper;
import by.lawaksoft.tradebot.mapper.WebSocketMapper;
import by.lawaksoft.tradebot.repository.mongo.CandlestickRepository;
import by.lawaksoft.tradebot.repository.InstrumentRepository;
import by.lawaksoft.tradebot.dto.websocket.ChannelInstDto;
import by.lawaksoft.tradebot.dto.websocket.WSRequestDto;
import by.lawaksoft.tradebot.dto.websocket.WSResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@Component
public class OkxWebsocketClient extends WebSocketClient {

	private final Logger logger = Logger.getLogger(OkxWebsocketClient.class.getName());
	private static final URI OKX_URI = URI.create("wss://ws.okx.com:8443/ws/v5/business");
	private final InstrumentRepository instrumentRepository;
	private final CandlestickRepository candlestickRepository;
	private final ObjectMapper objectMapper;

	public OkxWebsocketClient(InstrumentRepository instrumentRepository, CandlestickRepository candlestickRepository, ObjectMapper objectMapper) {

		super(OKX_URI);
		this.instrumentRepository = instrumentRepository;
		this.candlestickRepository = candlestickRepository;
		this.objectMapper = objectMapper;
	}

	@Override
	public void onOpen(ServerHandshake serverHandshake) {

		logger.info("Connected to okx websocket to URI " + OKX_URI);

		String message = getMessage();
		if (message != null) {
			super.send(message);
		} else {
			logger.info("Failed to send message to websocket server");
		}
	}

	private String getMessage() {

		List<ChannelInstDto> channelInstDtos = getChannelInstDtos();
		if (!channelInstDtos.isEmpty()) {
			WSRequestDto subscribeMessageObject = WSRequestDto.builder().op("subscribe").args(channelInstDtos).build();
			try {
				return objectMapper.writeValueAsString(subscribeMessageObject);
			} catch (JsonProcessingException e) {
				throw new JsonMapperException("object- " + subscribeMessageObject.toString());
			}
		}
		return null;
	}

	private List<ChannelInstDto> getChannelInstDtos() {

		return instrumentRepository.findAll().stream().map(WebSocketMapper::channel3MInstDto).toList();
	}

	@Override
	public void onMessage(String s) {

		try {
			WSResponseDto responseDto = objectMapper.readValue(s, WSResponseDto.class);
			if (responseDto.getData() != null) {
				saveDataToDB(responseDto.getData(), responseDto.getArg());
			} else if (!responseDto.getEvent().isEmpty()) {
				logger.info(s);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new JsonMapperException("string - " + s + "to " + WSResponseDto.class.getSimpleName());
		}
	}

	private void saveDataToDB(List<List<String>> data, ChannelInstDto args) {

		CandlestickDto candlestickDto = DtoMapper.toCandlestickDto(data.get(0).toArray(String[]::new));
		Instrument instrument = instrumentRepository.findByName(args.getInstId()).orElseThrow(EntityNotFoundException::new);
		Candlestick candlestick = DocumentMapper.toCandlestickDocument(candlestickDto, instrument);
		candlestickRepository.save(candlestick);
	}

	@Override
	public void onClose(int i, String s, boolean b) {

		if (!super.isClosed()) {
			super.close();
		}
		logger.info("Disconnected to okx websocket");
	}

	@Override
	public void onError(Exception e) {

		e.printStackTrace();
	}
}
