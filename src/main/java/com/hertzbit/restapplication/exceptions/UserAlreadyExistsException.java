package com.hertzbit.restapplication.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
