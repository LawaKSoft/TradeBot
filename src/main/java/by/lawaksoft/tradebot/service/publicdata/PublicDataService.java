package by.lawaksoft.tradebot.service.publicdata;

import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentDto;
import by.lawaksoft.tradebot.dto.request.InstrumentsFilterDto;

import java.util.List;

public interface PublicDataService {

    List<InstrumentDto> getInstruments(InstrumentsFilterDto filterDto);
}
