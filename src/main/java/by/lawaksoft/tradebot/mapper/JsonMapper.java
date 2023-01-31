package by.lawaksoft.tradebot.mapper;

import by.lawaksoft.tradebot.exception.dto.BusinessException;
import by.lawaksoft.tradebot.exception.dto.enums.ERROR_MESSAGE;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper {

    private JsonMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static String objectToJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new BusinessException(String.format("Cant map object to json %s", o.getClass()), ERROR_MESSAGE.CANT_MAP_OBJECT_TO_JSON);
        }
    }
}
