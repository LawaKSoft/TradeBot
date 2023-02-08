package by.lawaksoft.tradebot.client;

import by.lawaksoft.tradebot.dto.response.instrument.ResponseInstrumentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${PUBLIC_DATA}", url = "${PUBLIC_URL}")
public interface PublicClient {

    @GetMapping(value = "${INSTRUMENT}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    ResponseInstrumentDto getInstruments(@RequestParam("instType") String instType,
            @RequestParam(value = "uly", required = false) String uly,
            @RequestParam(value = "instFamily", required = false) String instFamily,
            @RequestParam(value = "instId", required = false) String instId);
}