package com.hertzbit.restapplication.exceptions;

public class UploadedFileNotFoundException extends RuntimeException{

    public UploadedFileNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
