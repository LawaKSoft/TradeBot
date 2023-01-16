package by.lawaksoft.tradebot.service.publicdata.impl;

import by.lawaksoft.tradebot.client.PublicClient;
import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentsDto;
import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentsFilterDto;
import by.lawaksoft.tradebot.dto.response.ResponseInstrumentDto;
import by.lawaksoft.tradebot.service.publicdata.PublicDataService;

public class PublicDataServiceImpl implements PublicDataService {

    private final PublicClient publicClient;

    public PublicDataServiceImpl(PublicClient publicClient) {
        this.publicClient = publicClient;
    }

    public InstrumentsDto getInstruments(InstrumentsFilterDto filterDto) {

        ResponseInstrumentDto instruments = publicClient.getInstruments(filterDto);
        return null;
    }
}
