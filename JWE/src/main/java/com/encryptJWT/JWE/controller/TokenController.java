package com.encryptJWT.JWE.controller;

import com.encryptJWT.JWE.beans.response.ServiceResponseBean;
import com.encryptJWT.JWE.utils.Jwe;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {

    private final Jwe jwe;
    @GetMapping("/create")
    public ServiceResponseBean create(@RequestParam String userId) throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException {
        String jwt = jwe.generateToken(userId);
        log.info("Inside demo token :: {}",jwt);
        return ServiceResponseBean.builder().data(jwt).message("Connected").status(Boolean.TRUE).build();
    }
    @GetMapping("/validate")
    public ServiceResponseBean validate(@RequestParam  String token) throws ParseException, JOSEException, CertificateException {

        return ServiceResponseBean.builder().status(jwe.validate(token)).build();
    }
}
