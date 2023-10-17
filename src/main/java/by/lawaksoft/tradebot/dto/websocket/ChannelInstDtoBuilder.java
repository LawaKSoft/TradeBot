package by.lawaksoft.tradebot.dto.websocket;

public class ChannelInstDtoBuilder {

	private String channel;
	private String instId;

	private ChannelInstDtoBuilder() {

	}

	public static ChannelInstDtoBuilder newBuilder() {

		return new ChannelInstDtoBuilder();
	}

	public ChannelInstDtoBuilder channel(String channel) {

		this.channel = channel;
		return this;
	}

	public ChannelInstDtoBuilder instId(String instId) {

		this.instId = instId;
		return this;
	}

	public ChannelInstDto build() {

		return new ChannelInstDto(channel, instId);
	}
}
