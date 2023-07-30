package com.CockroachDb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HashMap<String,Object>> handleResourceNotFoundException(ResourceNotFoundException e)
    {
        String message=e.getMessage();
        HashMap<String , Object> response =new HashMap<>();
            response.put("message",message);
            response.put("success",true);
            response.put("status", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
