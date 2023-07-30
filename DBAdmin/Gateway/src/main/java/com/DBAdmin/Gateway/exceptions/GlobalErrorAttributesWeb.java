package com.DBAdmin.Gateway.exceptions;


import com.DBAdmin.Gateway.models.ApiResponse;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalErrorAttributesWeb extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest webRequest, ErrorAttributeOptions options) {
        Map<String,Object> errorMap = new HashMap<>();

        Throwable throwable = getError(webRequest);
        ApiResponse response = ApiResponse.builder()
                .message(throwable.getMessage())
                .success(true)
                .status(HttpStatus.BAD_REQUEST).build();

        //errorMap.put("message",throwable.getMessage());
        errorMap.put("endPoint",webRequest.path());
        errorMap.put("response",response);
        return errorMap;
    }
}
