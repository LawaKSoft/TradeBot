package by.lawaksoft.tradebot.service.publicdata.impl;

import by.lawaksoft.tradebot.client.PublicClient;
import by.lawaksoft.tradebot.dto.model.InstrumentDto;
import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentsFilterDto;
import by.lawaksoft.tradebot.dto.response.ResponseInstrumentDto;
import by.lawaksoft.tradebot.mapper.DtoMapper;
import by.lawaksoft.tradebot.service.publicdata.PublicDataService;

import java.util.List;
import java.util.stream.Collectors;

public class PublicDataServiceImpl implements PublicDataService {

    private final PublicClient publicClient;

    public PublicDataServiceImpl(PublicClient publicClient) {
        this.publicClient = publicClient;
    }

    public List<InstrumentDto> getInstruments(InstrumentsFilterDto filterDto) {

        ResponseInstrumentDto responseInstrumentDto = publicClient.getInstruments(filterDto);
        return responseInstrumentDto.getInstrumentDtos().stream().map(DtoMapper::toInstrumentDto).collect(Collectors.toList());
    }
}
