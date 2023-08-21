package com.encryptJWT.JWE.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@Component
public class JweByJsonFilePublicKey {

    JWKSet localKeys = JWKSet.load(ResourceUtils.getFile("classpath:publicKey.json"));

    public JweByJsonFilePublicKey() throws IOException, ParseException {
    }

    public String generateToken(String userId) throws JOSEException, ParseException, JOSEException {

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID("123").build(),
                new JWTClaimsSet.Builder()
                        .subject(userId)
                        .issueTime(new Date())
                        .issuer("dbadmin")
                        .expirationTime(new Date(new Date().getTime() + 60 * 60 * 1000))
                        .build());
        JWK key = localKeys.getKeyByKeyId("123");
        // Sign the JWT
        signedJWT.sign(new RSASSASigner(key.toRSAKey()));

        // Create JWE object with signed JWT as payload
        JWEObject jweObject = new JWEObject(
                new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_512, EncryptionMethod.A256GCM)
                        .contentType("JWT") // required to indicate nested JWT
                        .build(),
                new Payload(signedJWT));

        // Encrypt with the recipient's public key
//        jweObject.encrypt(new RSAEncrypter());

        // Serialise to JWE compact form
//        String jweString = jweObject.serialize();


        //signed
        String jweString = signedJWT.serialize();
//        SignedJWT signedJWT1 = SignedJWT.parse(jweString);

//        System.out.println("valid "+signedJWT1.verify(new RSASSAVerifier(senderJWK.toPublicJWK())));
//        validate(jweString,senderJWK );
        return jweString;
    }


    public boolean isSignatureValid(String token) {

        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWK key = localKeys.getKeyByKeyId(signedJWT.getHeader().getKeyID());
            JWSVerifier verifier = new RSASSAVerifier( key.toRSAKey());
            return signedJWT.verify(verifier);
        } catch (ParseException | JOSEException e) {
            return false;
        }
    }
}
