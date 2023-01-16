package by.lawaksoft.tradebot.client;

import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentsFilterDto;
import by.lawaksoft.tradebot.dto.response.ResponseInstrumentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = ClientNavigation.PUBLIC, url = ClientNavigation.PUBLIC_URL)
public interface PublicClient {

    @GetMapping(value = ClientNavigation.INSTRUMENT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseInstrumentDto getInstruments(InstrumentsFilterDto filterDto);
}
