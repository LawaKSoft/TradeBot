package by.lawaksoft.tradebot.exception.controller;


import by.lawaksoft.tradebot.exception.dto.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleMethodFamilyNotFoundException(BusinessException businessException){
        if (businessException.getError_MESSAGE() == null && businessException.getHttpStatus() == HttpStatus.OK) {
            log.warn(BAD_REQUEST + " by {}", businessException.getMessage());

            return new ResponseEntity<>(businessException.getMessage(), BAD_REQUEST);
        }

        if (businessException.getHttpStatus() == HttpStatus.OK) {
            log.warn(BAD_REQUEST + " by {}", businessException.getMessage());

            return new ResponseEntity<>(businessException.getError_MESSAGE().getMessage(), BAD_REQUEST);
        }

        if(businessException.getError_MESSAGE() == null) {
            log.warn(businessException.getHttpStatus() + " by {}", businessException.getMessage());

            return new ResponseEntity<>(businessException.getMessage(), businessException.getHttpStatus());
        }

        log.warn(businessException.getHttpStatus() + " by {}", businessException.getMessage());

        return new ResponseEntity<>(businessException.getError_MESSAGE().getMessage(), businessException.getHttpStatus());
    }
}
