package by.lawaksoft.tradebot.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseInstrumentDto extends ResponseDto{

	private final List<FullInstrumentDto> instrumentDtos;

	public ResponseInstrumentDto(@JsonProperty("code") String code, @JsonProperty("msg") String message, @JsonProperty("data") List<FullInstrumentDto> instrumentDtos) {

		super(code, message);
		this.instrumentDtos = instrumentDtos;
	}

	public List<FullInstrumentDto> getInstrumentDtos() {

		return instrumentDtos;
	}
}
