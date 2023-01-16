package by.lawaksoft.tradebot.controller.publicdata;

import by.lawaksoft.tradebot.controller.Navigation;
import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentsDto;
import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentsFilterDto;
import by.lawaksoft.tradebot.service.publicdata.PublicDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(Navigation.PUBLIC)
public class PublicDataController {

    private final PublicDataService publicDataService;

    public PublicDataController(PublicDataService publicDataService) {
        this.publicDataService = publicDataService;
    }

    @GetMapping(value = Navigation.INSTRUMENTS)
    public ResponseEntity<InstrumentsDto> getInstruments(InstrumentsFilterDto filterDto) {

        InstrumentsDto instruments = publicDataService.getInstruments(filterDto);
        return ResponseEntity.ok(instruments);
    }
}
