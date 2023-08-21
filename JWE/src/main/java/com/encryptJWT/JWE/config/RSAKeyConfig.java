package com.encryptJWT.JWE.config;


import com.encryptJWT.JWE.utils.JwtJsonWebEncryption;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.text.ParseException;

@Configuration
public class RSAKeyConfig {

    @Bean
    public JwtJsonWebEncryption jweconfig() throws JOSEException, IOException, ParseException {
        return new JwtJsonWebEncryption(publicSignRSAKey() , encryptKey());
    }

    public RSAKey publicSignRSAKey() throws JOSEException {
        RSAKey publicSignRSAKey = new RSAKeyGenerator(2048)
                .keyID("123")
                .keyUse(KeyUse.SIGNATURE)
                .generate();

        return publicSignRSAKey;
    }

    public RSAKey encryptKey() throws JOSEException {
        RSAKey encryptKey= new RSAKeyGenerator(2048)
                .keyID("456")
                .keyUse(KeyUse.ENCRYPTION)
                .generate();

        return encryptKey;
    }

//    public RSAKey RSACustomGen(){
//        String key ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhkBap4F9Nz9DR9wjae+qFAhgHNgH0lAjWXNtrHZyezN+y4A6ZvVcUJwsFj5uh89esD350QmY8BYYCnyAe1q1XhhvxbN/waTXsgV7KMe7k3QKyq5To5P/n9PMpDzeFuPUVzi/zZrrHKny/XE4hNtYJHsZiwcOvSHWhAW+e8sNPQlkTtKR2ZrvbzMI4osWWa/+UBLmGYJ5FqMU6WhE5J7m33zk9gia+wUn7/dm+zA4u2UG2T24EuxQnmmlBHjuIPZgzzk+4q+3yrOr58g+r58BMYZdBmiPmm0NOoeUqVVfD0g6ahyXT/w+S8+3N/y4pVfGgvJyWL4NgYlHmKgbwSU8+wIDAQAB";
//
//        return
//    }



}
