package by.lawaksoft.tradebot.dto.websocket;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelInstDto {

	private String channel;
	private String instId;

	public ChannelInstDto(String channel, String instId) {

		this.channel = channel;
		this.instId = instId;
	}

	public ChannelInstDto() {

	}
}
