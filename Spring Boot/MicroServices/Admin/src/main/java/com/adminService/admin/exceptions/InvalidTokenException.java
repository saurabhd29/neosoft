package com.adminService.admin.exceptions;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException()
    {
        super("Resource not found on server!!");
    }

    public InvalidTokenException(String message)
    {
        super(message);
    }
}
