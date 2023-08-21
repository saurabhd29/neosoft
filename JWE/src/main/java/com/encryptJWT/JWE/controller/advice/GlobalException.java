package com.encryptJWT.JWE.controller.advice;

import com.encryptJWT.JWE.beans.response.ServiceResponseBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.crypto.BadPaddingException;
import java.text.ParseException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(BadPaddingException.class)
    public ServiceResponseBean InValidKey(BadPaddingException ex){
        return ServiceResponseBean.builder()
                .error(ex.getMessage())
                .status(Boolean.FALSE)
                .build();
    }

    @ExceptionHandler(ParseException.class)
    public ServiceResponseBean InValidToken(ParseException ex){
        return ServiceResponseBean.builder()
                .error(ex.getMessage())
                .status(Boolean.FALSE)
                .build();
    }
}
