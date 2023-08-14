package com.service.otp.service;

import com.service.otp.beans.request.OtpRequestBean;
import com.service.otp.beans.request.OtpValidateRequestBean;
import com.service.otp.beans.response.ServiceResponseBean;

public interface IOtpService {

    public ServiceResponseBean generateOtp(OtpRequestBean requestBean);

    public ServiceResponseBean validateOtp(OtpValidateRequestBean requestBean);
}
