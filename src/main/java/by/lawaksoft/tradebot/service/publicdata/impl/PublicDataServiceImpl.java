package by.lawaksoft.tradebot.service.publicdata.impl;

import by.lawaksoft.tradebot.client.PublicClient;
import by.lawaksoft.tradebot.dto.model.publicdata.InstrumentDto;
import by.lawaksoft.tradebot.dto.request.InstrumentsFilterDto;
import by.lawaksoft.tradebot.dto.response.ResponseInstrumentDto;
import by.lawaksoft.tradebot.mapper.DtoMapper;
import by.lawaksoft.tradebot.service.publicdata.PublicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicDataServiceImpl implements PublicDataService {

    private final PublicClient publicClient;

    @Autowired
    public PublicDataServiceImpl(PublicClient publicClient) {
        this.publicClient = publicClient;
    }

    public List<InstrumentDto> getInstruments(InstrumentsFilterDto filterDto) {

        ResponseInstrumentDto responseInstrumentDto = publicClient.getInstruments(filterDto.getInstType(), filterDto.getUly(), filterDto.getInstFamily(), filterDto.getInstId());
        return responseInstrumentDto.getInstrumentDtos().stream().map(DtoMapper::toInstrumentDto).collect(Collectors.toList());
    }
}
