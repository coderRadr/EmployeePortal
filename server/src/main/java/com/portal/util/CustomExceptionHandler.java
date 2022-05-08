package com.portal.util;

import com.portal.models.ErrorModel;
import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * Global Exception Handler for this Application
 */
@Log4j2
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorModel> handle(Exception exp) {
        log.error(exp.getMessage(), exp);
        if (exp instanceof HttpStatusCodeException) {
            HttpStatusCodeException httpStatusCodeException = (HttpStatusCodeException) exp;
            String errorMessage = httpStatusCodeException.getMessage();
            HttpStatus httpStatus = httpStatusCodeException.getStatusCode();
            return new ResponseEntity<>(new ErrorModel(httpStatus, errorMessage, LocalDateTime.now(), MDC.get(ConstantUtil.REQUEST_ID)), httpStatus);
        } else {
            return new ResponseEntity<>(new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR, exp.getMessage(), LocalDateTime.now(), MDC.get(ConstantUtil.REQUEST_ID)), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
