package by.lawaksoft.tradebot.mapper;

import by.lawaksoft.tradebot.entity.Instrument;
import by.lawaksoft.tradebot.dto.websocket.ChannelInstDto;
import by.lawaksoft.tradebot.dto.websocket.ChannelInstDtoBuilder;

public class WebSocketMapper {

	private WebSocketMapper() {

	}

	public static ChannelInstDto channel3MInstDto(String instId) {

		return ChannelInstDtoBuilder.newBuilder()
				.channel("candle3M")
				.instId(instId)
				.build();
	}

	public static ChannelInstDto channel3MInstDto(Instrument instrument) {

		return ChannelInstDtoBuilder.newBuilder()
				.channel("candle3M")
				.instId(instrument.getInstrumentId())
				.build();
	}
}
