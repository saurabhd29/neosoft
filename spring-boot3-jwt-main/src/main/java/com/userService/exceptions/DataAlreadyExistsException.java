package com.userService.exceptions;

public class DataAlreadyExistsException extends IllegalStateException{
    public DataAlreadyExistsException(String message){
        super(message);
    }
    public DataAlreadyExistsException(String message, Throwable throwable){
        super(message, throwable);
    }
}
