package com.userService.exceptions;

import com.userService.dto.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException ex){
        String msg = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(msg).success(true).status(HttpStatus.NOT_FOUND).build();
        log.info("ResourceNotFoundException : {}",response.toString() );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InActiveUserException.class)
    public ResponseEntity<?> InActiveUserException(InActiveUserException ex){
        String msg = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(msg).success(true).status(HttpStatus.NOT_FOUND).build();
        log.info("InActiveUserException : {}",response.toString() );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<?> handlerInvalidTokenException(InvalidTokenException ex){
        String msg = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(msg).success(true).status(HttpStatus.FORBIDDEN).build();
        log.info("InvalidTokenException : {}",response.toString() );
        return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> Exception(Exception ex){
        String msg = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(msg).success(true).status(HttpStatus.FORBIDDEN).build();
        log.info("Exception : {}",response.toString() );
        return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> ExpiredJwtException(ExpiredJwtException ex){
        String msg = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(msg).success(true).status(HttpStatus.FORBIDDEN).build();
        log.info("ExpiredJwtException : {}",response.toString() );
        return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
    }

}
