package com.microservice.orderservice.exceptions;


import com.microservice.orderservice.dto.response.ApiResponse;
import com.microservice.orderservice.dto.response.ServiceResponseBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ServiceResponseBean resourceNotFoundException(ResourceNotFoundException ex){
        ApiResponse apiResponse = ApiResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .success(Boolean.FALSE)
                .build();
        return ServiceResponseBean.builder()
                .status(Boolean.FALSE)
                .message("Data does not exists...")

                .build();
    }
    @ExceptionHandler(Exception.class)
    public ServiceResponseBean Exception(Exception ex){
        ApiResponse apiResponse = ApiResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .success(Boolean.FALSE)
                .build();
        return ServiceResponseBean.builder()
                .status(Boolean.FALSE)
                .message("Data does not exists...")
                .data(apiResponse)
                .build();
    }
}
