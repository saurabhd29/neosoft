package com.encryptJWT.JWE.config;

import com.encryptJWT.JWE.utils.Jwe;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RSAKeyConfig {

    @Bean
    public Jwe jweconfig() throws JOSEException {
        return new Jwe(publicSignRSAKey() , encryptKey());
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



}
