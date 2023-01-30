package by.lawaksoft.tradebot.exception.dto;

import by.lawaksoft.tradebot.exception.dto.enums.ERROR_MESSAGE;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class BusinessException extends RuntimeException{

    private ERROR_MESSAGE error_MESSAGE;
    private HttpStatus httpStatus = HttpStatus.OK;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public BusinessException(String message, ERROR_MESSAGE error_MESSAGE) {
        super(message);
        this.error_MESSAGE = error_MESSAGE;
    }

    public BusinessException(String message, ERROR_MESSAGE error_MESSAGE, HttpStatus httpStatus) {
        super(message);
        this.error_MESSAGE = error_MESSAGE;
        this.httpStatus = httpStatus;
    }
}
