package by.lawaksoft.tradebot.entity.enums;

import by.lawaksoft.tradebot.exception.dto.BusinessException;
import by.lawaksoft.tradebot.exception.dto.enums.ERROR_MESSAGE;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Locale;

@AllArgsConstructor
public enum AlgorithmBotType {

    GRID("Grid");

    private final String text;

    public String text() {
        return this.text;
    }


    public static AlgorithmBotType byText(String text) throws BusinessException {
        try {
            return AlgorithmBotType.valueOf(text.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new BusinessException(String.format("Enum by text %s not found", text), ERROR_MESSAGE.ENUM_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
