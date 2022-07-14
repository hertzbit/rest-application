package com.hertzbit.restapplication.exceptions;

import com.hertzbit.restapplication.model.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    private final String TIMESTAMP_FORMAT = "yyyy.MM.dd'T'HH.mm.ss.SSSXXX";

    @ExceptionHandler (value = UserNotFoundException.class)
    public ResponseEntity<Object> userNotFoundException (UserNotFoundException userNotFound) {

        ExceptionMessage exceptionMessage = new ExceptionMessage(404, userNotFound.getMessage(),
                new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date()));
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }

}
