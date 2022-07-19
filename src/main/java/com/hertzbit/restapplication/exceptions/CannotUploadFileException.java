package com.hertzbit.restapplication.exceptions;

public class CannotUploadFileException extends RuntimeException{

    public CannotUploadFileException (String errorMessage) {
        super (errorMessage);
    }
}
