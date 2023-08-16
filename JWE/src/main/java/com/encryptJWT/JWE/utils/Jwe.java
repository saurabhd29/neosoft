package com.encryptJWT.JWE.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;


public class Jwe {

    public  Jwe(RSAKey publicRSAKey,RSAKey recipientJWK ){
        this.publicSignRSAKey = publicRSAKey;
        this.encryptKey = recipientJWK;
    }
    final RSAKey publicSignRSAKey;
    final RSAKey encryptKey;
    public String generateToken(String userId) throws JOSEException, ParseException, JOSEException {
//        RSAKey senderJWK = new RSAKeyGenerator(2048)
//                .keyID("123")
//                .keyUse(KeyUse.SIGNATURE)
//                .generate();
//        publicRSAKey =senderJWK;
//        RSAKey senderPublicJWK = senderJWK.toPublicJWK();
//
//
//        recipientJWK = new RSAKeyGenerator(2048)
//                .keyID("456")
//                .keyUse(KeyUse.ENCRYPTION)
//                .generate();
//        RSAKey recipientPublicJWK = recipientJWK.toPublicJWK();
        // Create JWT
        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(publicSignRSAKey.getKeyID()).build(),
                new JWTClaimsSet.Builder()
                        .subject(userId)
                        .issueTime(new Date())
                        .issuer("dbadmin")
                        .expirationTime(new Date(new Date().getTime() + 60 * 60 * 1000))
                        .build());

        // Sign the JWT
        signedJWT.sign(new RSASSASigner(publicSignRSAKey));

        // Create JWE object with signed JWT as payload
        JWEObject jweObject = new JWEObject(
                new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_512, EncryptionMethod.A256GCM)
                        .contentType("JWT") // required to indicate nested JWT
                        .build(),
                new Payload(signedJWT));

        // Encrypt with the recipient's public key
        jweObject.encrypt(new RSAEncrypter(encryptKey.toPublicJWK()));

        // Serialise to JWE compact form
        String jweString = jweObject.serialize();


        //signed
//        String jweString = signedJWT.serialize();
//        SignedJWT signedJWT1 = SignedJWT.parse(jweString);

//        System.out.println("valid "+signedJWT1.verify(new RSASSAVerifier(senderJWK.toPublicJWK())));
//        validate(jweString,senderJWK );
        return jweString;
    }

    public boolean validate(String jweString) throws ParseException, JOSEException {

        JWEObject jweObject = JWEObject.parse(jweString);

//          Decrypt with private key
        jweObject.decrypt(new RSADecrypter(encryptKey));

        SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();
        Boolean valid = signedJWT.verify(new RSASSAVerifier(publicSignRSAKey));
        System.out.println("validate "+ valid);
        System.out.println("sub "+signedJWT.getJWTClaimsSet().getSubject());
        return valid;
    }


}
