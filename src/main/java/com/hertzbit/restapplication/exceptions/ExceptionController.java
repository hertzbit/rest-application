package com.hertzbit.restapplication.exceptions;

import com.hertzbit.restapplication.model.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
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

    @ExceptionHandler (value = NoBookFoundException.class)
    public ResponseEntity<Object> noBookFoundException (NoBookFoundException noBookFound) {

        ExceptionMessage exceptionMessage = new ExceptionMessage(404, noBookFound.getMessage(),
                new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date()));
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler (value = NoStudentFoundException.class)
    public ResponseEntity<Object> noStudentFoundException (NoStudentFoundException noStudentFound) {

        ExceptionMessage exceptionMessage = new ExceptionMessage(404, noStudentFound.getMessage(),
                new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date()));
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler (value = UserAlreadyExistsException.class)
    public ResponseEntity<Object> userAlreadyExistsException (UserAlreadyExistsException userAlreadyExistsException) {

        ExceptionMessage exceptionMessage = new ExceptionMessage(400, userAlreadyExistsException.getMessage(),
                new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date()));
        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (value = BookWithSameISBNExistsException.class)
    public ResponseEntity<Object> bookWithSameISBNExistsException (BookWithSameISBNExistsException bookWithSameISBNExists) {

        ExceptionMessage exceptionMessage = new ExceptionMessage(400, bookWithSameISBNExists.getMessage(),
                new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date()));
        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (value = CannotUploadFileException.class)
    public ResponseEntity<Object> cannotUploadFileException (CannotUploadFileException cannotUploadFileException) {

        ExceptionMessage exceptionMessage = new ExceptionMessage(500, cannotUploadFileException.getMessage(),
                new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date()));
        return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler (value = UploadedFileNotFoundException.class)
    public ResponseEntity<Object> uploadedFileNotFoundException (UploadedFileNotFoundException uploadedFileNotFoundException) {

        ExceptionMessage exceptionMessage = new ExceptionMessage(404, uploadedFileNotFoundException.getMessage(),
                new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date()));
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler (value = MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> maxUploadSizeExceededException (MaxUploadSizeExceededException maxUploadSizeExceededException) {

        ExceptionMessage exceptionMessage = new ExceptionMessage(400, "Maximum allowed file size is 1 MB. " +
                "Current File being uploaded exceeds this limit",
                new SimpleDateFormat(TIMESTAMP_FORMAT).format(new Date()));
        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }
}
