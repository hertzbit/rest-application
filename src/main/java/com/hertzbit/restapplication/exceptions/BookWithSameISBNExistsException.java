package com.hertzbit.restapplication.exceptions;

public class BookWithSameISBNExistsException extends RuntimeException{

    public BookWithSameISBNExistsException(String errorMessage) {
        super(errorMessage);
    }
}
