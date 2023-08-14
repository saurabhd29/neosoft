package com.service.otp.service.impl;

import com.service.otp.beans.request.OtpRequestBean;
import com.service.otp.beans.request.OtpValidateRequestBean;
import com.service.otp.beans.response.OtpResponseBean;
import com.service.otp.beans.response.ServiceResponseBean;
import com.service.otp.repository.OtpRepository;
import com.service.otp.service.IOtpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
@Slf4j
@Service
public class OtpServiceImpl implements IOtpService {

    @Autowired
    private OtpRepository otpRepository;

    public ServiceResponseBean generateOtp(OtpRequestBean requestBean){
        int otp = generateOtp();
        log.info("otp generated -> {}",otp);
        otpRepository.put(requestBean.getEmail(),String.valueOf(otp));
        return ServiceResponseBean.builder()
                .data(OtpResponseBean.builder().otp(String.valueOf(otp)).validTill("60 seconds").build())
                .httpStatus(HttpStatus.CREATED)
                .status(Boolean.TRUE)
                .build();
    }

    public ServiceResponseBean validateOtp(OtpValidateRequestBean requestBean){
        Optional<Object> otp = otpRepository.get(requestBean.getEmail());
        log.info("otp-> {}",otp);
        if(otp.isEmpty()){
            return ServiceResponseBean.builder()
                    .status(Boolean.FALSE)
                    .httpStatus(HttpStatus.FORBIDDEN)
                    .message("Invalid")
                    .errors("Invalid")
                    .build();
        }
        else {
            String otpString = String.valueOf(requestBean.getOtp());
            Boolean validOtp = otp.get().equals(otpString);
            log.info("valid for {} :: {}",otpString,otp.get().equals(requestBean.getOtp()));
            if(validOtp){
                otpRepository.remove(requestBean.getEmail());
                return ServiceResponseBean.builder()
                        .status(Boolean.TRUE)
                        .httpStatus(HttpStatus.ACCEPTED)
                        .message("Valid")

                        .build();
            }
        }
        return ServiceResponseBean.builder()
                .status(Boolean.FALSE)
                .httpStatus(HttpStatus.FORBIDDEN)
                .message("Invalid")
                .errors("Invalid")
                .build();
    }


    public Integer generateOtp() {

        Random random = new Random();
        return random.nextInt((900001)+100000);
    }
}
