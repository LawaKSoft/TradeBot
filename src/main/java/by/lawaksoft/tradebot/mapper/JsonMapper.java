package by.lawaksoft.tradebot.mapper;

import by.lawaksoft.tradebot.exception.entity.BusinessException;
import by.lawaksoft.tradebot.exception.entity.enums.ERROR_CODE;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class JsonMapper {

    public static String objectToJson(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new BusinessException(String.format("Cant map object to json %s", o.getClass()), ERROR_CODE.CANT_MAP_OBJECT_TO_JSON);
        }
    }
}
