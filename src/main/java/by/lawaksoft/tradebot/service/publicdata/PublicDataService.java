package by.lawaksoft.tradebot.service.publicdata;

import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentsDto;
import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentsFilterDto;

public interface PublicDataService {

    InstrumentsDto getInstruments(InstrumentsFilterDto filterDto);
}
