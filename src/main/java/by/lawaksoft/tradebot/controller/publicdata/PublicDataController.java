package by.lawaksoft.tradebot.controller.publicdata;

import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentDto;
import by.lawaksoft.tradebot.dto.request.InstrumentsFilterDto;
import by.lawaksoft.tradebot.service.publicdata.PublicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicDataController {

    private final PublicDataService publicDataService;

    @Autowired
    public PublicDataController(PublicDataService publicDataService) {
        this.publicDataService = publicDataService;
    }

    @GetMapping(value = "/instruments")
    public ResponseEntity<List<InstrumentDto>> getInstruments(@RequestBody InstrumentsFilterDto filterDto) {

        var instruments = publicDataService.getInstruments(filterDto);
        return ResponseEntity.ok(instruments);
    }
}
