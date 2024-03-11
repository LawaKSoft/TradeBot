package by.lawaksoft.tradebot.dto.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WSResponseDto {

	private String event;
	private ChannelInstDto arg;
	private String code;
	private String message;
	private List<List<String>> data;
}
