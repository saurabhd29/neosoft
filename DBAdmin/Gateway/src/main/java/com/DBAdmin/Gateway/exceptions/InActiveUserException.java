package com.DBAdmin.Gateway.exceptions;

public class InActiveUserException extends RuntimeException{

    public InActiveUserException()
    {
        super("User InActive");
    }

    public InActiveUserException(String message)
    {
        super(message);
    }
}
