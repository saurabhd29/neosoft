package com.service.otp.controller;

import com.service.otp.beans.request.OtpRequestBean;
import com.service.otp.beans.request.OtpValidateRequestBean;
import com.service.otp.beans.response.ServiceResponseBean;
import com.service.otp.service.IOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp-service")
public class OtpController {

    @Autowired
    private IOtpService otpService;

    @PostMapping("/generate-otp")
    public ServiceResponseBean generateOtp(@RequestBody OtpRequestBean requestBean){
        return otpService.generateOtp(requestBean);
    }

    @PostMapping("/validate-otp")
    public ServiceResponseBean generateOtp(@RequestBody OtpValidateRequestBean requestBean){
        return otpService.validateOtp(requestBean);
    }


}
