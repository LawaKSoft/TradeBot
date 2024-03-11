package by.lawaksoft.tradebot.dto.manager;

import by.lawaksoft.tradebot.entity.AlgoParam;
import by.lawaksoft.tradebot.entity.AlgoType;
import by.lawaksoft.tradebot.entity.Instrument;
import by.lawaksoft.tradebot.entity.User;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@SuperBuilder
public class AlgoInstanceDto {

    Long id;
    AlgoType algoType;
    User user;
    Set<AlgoParam> parameters;
    Instrument instrument;
}
