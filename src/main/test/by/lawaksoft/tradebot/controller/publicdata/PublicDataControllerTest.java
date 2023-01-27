package by.lawaksoft.tradebot.controller.publicdata;

import by.lawaksoft.tradebot.controller.Navigation;
import by.lawaksoft.tradebot.dto.request.InstrumentsFilterDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PublicDataControllerTest {

	protected static final MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void getInstruments() throws Exception {

		InstrumentsFilterDto filterDto = new InstrumentsFilterDto("SPOT", null, null, "BTC-USDT");
		this.mockMvc.perform(get(Navigation.PUBLIC + Navigation.INSTRUMENTS)
						.contentType(CONTENT_TYPE)
						.content(asString(filterDto)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andDo(print());
	}

	protected <T> String asString(T object) {

		try {
			return objectMapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}