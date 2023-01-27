package by.lawaksoft.tradebot.client;

import by.lawaksoft.tradebot.dto.response.ResponseInstrumentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ClientNavigation.PUBLIC, url = ClientNavigation.PUBLIC_URL)
public interface PublicClient {

    @GetMapping(value = ClientNavigation.INSTRUMENT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseInstrumentDto getInstruments(@RequestParam(ClientNavigation.INSTRUMENT_TYPE) String instType,
            @RequestParam(value = ClientNavigation.ULY, required = false) String uly,
            @RequestParam(value = ClientNavigation.INSTRUMENT_FAMILY, required = false) String instFamily,
            @RequestParam(value = ClientNavigation.INSTRUMENT_ID, required = false) String instId);
}