package com.service.otp.controller.advice;

import com.service.otp.beans.response.ServiceResponseBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> ex(Exception e){

        return ResponseEntity.ok(ServiceResponseBean.builder().errors(e.getMessage()).build());
    }


}
