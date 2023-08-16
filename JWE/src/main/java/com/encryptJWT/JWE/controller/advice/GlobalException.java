package com.encryptJWT.JWE.controller.advice;

import com.encryptJWT.JWE.beans.response.ServiceResponseBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.crypto.BadPaddingException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(BadPaddingException.class)
    public ServiceResponseBean InValidKey(BadPaddingException ex){
        return ServiceResponseBean.builder()
                .error(ex.getMessage())
                .status(Boolean.FALSE)
                .build();
    }
}
