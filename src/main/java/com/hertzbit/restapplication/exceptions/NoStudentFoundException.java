package com.hertzbit.restapplication.exceptions;

public class NoStudentFoundException extends RuntimeException{

    public NoStudentFoundException(String errorMessage) {
        super(errorMessage);
    }
}
