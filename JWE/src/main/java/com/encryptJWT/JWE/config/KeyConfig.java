package com.encryptJWT.JWE.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

@Configuration
public class KeyConfig {

    @Bean
    public KeyFactory keyFactory() throws NoSuchAlgorithmException {

        // Create KeyFactory and RSA Keys Specs
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory;
    }

    @Bean
    public KeyPair keyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        //Initialize key size
        keyPairGenerator.initialize(2048);
        // Generate the key pair
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        return keyPair;
    }

    @Bean
    public RSAPublicKeySpec rsaPublicKeySpec() throws NoSuchAlgorithmException, InvalidKeySpecException {
    RSAPublicKeySpec publicKeySpec = keyFactory().getKeySpec(keyPair().getPublic(), RSAPublicKeySpec.class);
        return publicKeySpec;
    }

    @Bean
    public RSAPrivateKeySpec rsaPrivateKeySpec() throws NoSuchAlgorithmException, InvalidKeySpecException {
    RSAPrivateKeySpec privateKeySpec = keyFactory().getKeySpec(keyPair().getPrivate(), RSAPrivateKeySpec.class);

        return privateKeySpec;
    }


//
//    // Generate (and retrieve) RSA Keys from the KeyFactory using Keys Specs
//    RSAPublicKey publicRsaKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
//    RSAPrivateKey privateRsaKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);

}
