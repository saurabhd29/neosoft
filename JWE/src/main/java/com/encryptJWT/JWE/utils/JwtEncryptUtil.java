package com.encryptJWT.JWE.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Date;

@Component
public class JwtEncryptUtil {

    public String generateToken() throws NoSuchAlgorithmException, InvalidKeySpecException, JOSEException, ParseException, JOSEException {
        RSAKey senderJWK = new RSAKeyGenerator(2048)
                .keyID("123")
                .keyUse(KeyUse.SIGNATURE)
                .generate();
        RSAKey senderPublicJWK = senderJWK.toPublicJWK();
        RSAKey recipientJWK = new RSAKeyGenerator(2048)
                .keyID("456")
                .keyUse(KeyUse.ENCRYPTION)
                .generate();
        RSAKey recipientPublicJWK = recipientJWK.toPublicJWK();


        JWSSigner signer = new RSASSASigner(senderJWK);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject("1")
                .issuer("DB")
                .expirationTime(new Date(new Date().getTime() + 60 * 1000))
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(senderJWK.getKeyID()).build(),
                claimsSet);


        signedJWT.sign(signer);
        String s = signedJWT.serialize();

        validateToken(s,recipientJWK);
        JWSVerifier verifier = new RSASSAVerifier(recipientPublicJWK);
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
    public boolean validateToken(String token, RSAKey recipientJWK) throws ParseException, JOSEException {

        SignedJWT signedJWT = SignedJWT.parse(token);

        JWSVerifier verifier = new RSASSAVerifier(recipientJWK.toPublicJWK());
        System.out.println("Valid "+signedJWT.verify(verifier));
        return false;
    }

}
