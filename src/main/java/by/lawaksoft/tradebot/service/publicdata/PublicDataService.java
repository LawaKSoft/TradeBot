package by.lawaksoft.tradebot.service.publicdata;

import by.lawaksoft.tradebot.dto.model.InstrumentDto;
import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentsFilterDto;

import java.util.List;

public interface PublicDataService {

    List<InstrumentDto> getInstruments(InstrumentsFilterDto filterDto);
}
