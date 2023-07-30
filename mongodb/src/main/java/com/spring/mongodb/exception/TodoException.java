package com.spring.mongodb.exception;

public class TodoException extends Exception{

    public TodoException(String message){
        super(message);
    }

    public static String StringNotFound(){
        return "StringNotFound  ";
    }

    public static String StringExistsFound(){
        return "StringExistsFound  ";
    }
}
