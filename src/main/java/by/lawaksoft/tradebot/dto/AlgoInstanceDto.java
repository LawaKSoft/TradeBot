package by.lawaksoft.tradebot.dto;

import by.lawaksoft.tradebot.entity.AlgoType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class AlgoInstanceDto {

    AlgoType algoType;
}
