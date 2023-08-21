package com.encryptJWT.JWE.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.ParseException;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtKeysSign {
    private final KeyFactory keyFactory;
    private final RSAPublicKeySpec publicKeySpec;
    private final RSAPrivateKeySpec privateKeySpec;
    public String generateToken(String userId) throws NoSuchAlgorithmException, InvalidKeySpecException, JOSEException, ParseException, JOSEException {

    RSAPublicKey publicRsaKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);

    RSAPrivateKey privateRsaKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);


        JWSSigner signer = new RSASSASigner(privateRsaKey);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(userId)
                .issuer("DB")
                .expirationTime(new Date(new Date().getTime() + 60 * 1000))
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).build(),
                claimsSet);


        signedJWT.sign(signer);
        String s = signedJWT.serialize();

        JWSVerifier verifier = new RSASSAVerifier(publicRsaKey);
        System.out.println("Valid "+signedJWT.verify(verifier));
        return s;
    }

//    public static RSAKey rsaJwtKey() throws JOSEException {
//        return new RSAKeyGenerator(2048)
//                .keyID("123")
//                .generate();
//    }
//
//    public static RSAKey getPublicKey(RSAKey rsaJwtKey){
//        return rsaJwtKey.toPublicJWK();
//    }
    public boolean validateToken(String token) throws ParseException, JOSEException, InvalidKeySpecException {
        RSAPublicKey publicRsaKey = (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);

        SignedJWT signedJWT = SignedJWT.parse(token);

        JWSVerifier verifier = new RSASSAVerifier(publicRsaKey);
        Boolean valid = signedJWT.verify(verifier);
        System.out.println("validateToken "+ valid);
        return valid;
    }

}
