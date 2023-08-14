package com.service.otp.beans.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponseBean {

    private String message;

    private Object data;

    private String errors;

    private HttpStatus httpStatus;

    private Boolean status;
}
