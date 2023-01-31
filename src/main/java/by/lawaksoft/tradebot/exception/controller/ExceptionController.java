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

    private static final String BY = " by {}";

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleMethodFamilyNotFoundException(BusinessException businessException){
        if (businessException.getErrorMessage() == null && businessException.getHttpStatus() == HttpStatus.OK) {
            log.warn(BAD_REQUEST + BY, businessException.getMessage());

            return new ResponseEntity<>(businessException.getMessage(), BAD_REQUEST);
        }

        if (businessException.getHttpStatus() == HttpStatus.OK) {
            log.warn(BAD_REQUEST + BY, businessException.getMessage());

            return new ResponseEntity<>(businessException.getErrorMessage().getMessage(), BAD_REQUEST);
        }

        if(businessException.getErrorMessage() == null) {
            log.warn(businessException.getHttpStatus() + BY, businessException.getMessage());

            return new ResponseEntity<>(businessException.getMessage(), businessException.getHttpStatus());
        }

        log.warn(businessException.getHttpStatus() + BY, businessException.getMessage());

        return new ResponseEntity<>(businessException.getErrorMessage().getMessage(), businessException.getHttpStatus());
    }
}
