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

    private ERROR_MESSAGE errorMessage;
    private HttpStatus httpStatus = HttpStatus.OK;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public BusinessException(String message, ERROR_MESSAGE errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public BusinessException(String message, ERROR_MESSAGE errorMessage, HttpStatus httpStatus) {
        super(message);
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
