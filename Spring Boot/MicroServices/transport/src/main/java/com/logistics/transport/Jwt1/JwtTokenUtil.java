/*
package com.logistics.transport.Jwt1;

import java.util.Date;

import com.logistics.transport.Entites.TransportDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenUtil {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour


    private String SECRET_KEY="hello";

    public String generateAccessToken(TransportDetails user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getTransporterId(), user.getEmail()))
                .setIssuer("CodeJava")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

    }
}
*/
