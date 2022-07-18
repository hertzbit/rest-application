package com.hertzbit.restapplication.exceptions;

public class NoBookFoundException extends RuntimeException{

    public NoBookFoundException (String errorMessage) {
        super(errorMessage);
    }
}
