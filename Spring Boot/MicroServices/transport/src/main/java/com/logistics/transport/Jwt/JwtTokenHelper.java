/*
package com.logistics.transport.Jwt;

import com.logistics.transport.Entites.TransportDetails;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenHelper {

    public static final long JWT_TOKEN_VALIDITY = 1 * 60 * 60 * 1000; //1HOUR

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenHelper.class);

    private String SECRET_KEY= "hello";

    public  String generateAccessToken(TransportDetails user){
        return Jwts.builder()
                .setSubject(String.format("%d %s",user.getTransporterId(),user.getEmail()))
                .setIssuer("JWT App")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }
    public boolean validateAccessToken(String token)
    {
        try
        {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }
        catch(ExpiredJwtException e)
        {
            LOGGER.error("JWT expired",e.getMessage());
        }
        catch(IllegalArgumentException e)
        {
            LOGGER.error("Token is null or empty",e.getMessage());
        }
        catch(MalformedJwtException e)
        {
            LOGGER.error("JWT is invalid",e.getMessage());
        }
        catch(UnsupportedJwtException e)
        {
            LOGGER.error("JWT is not supported",e.getMessage());
        }
        catch(SignatureException e)
        {
            LOGGER.error("Signature validation failed",e.getMessage());
        }
        return false;
    }

    public Claims parseClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }
}
*/
