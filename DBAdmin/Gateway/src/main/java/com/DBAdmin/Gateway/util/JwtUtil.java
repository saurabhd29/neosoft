package com.DBAdmin.Gateway.util;

import com.DBAdmin.Gateway.models.User;

import com.DBAdmin.Gateway.models.UserGatewayResponseBean;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtil {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    public String getSubject(String token)
    {
          return extractAllClaims(token).getSubject();
    }
   /* public void validateToken(final String token) {
        System.out.println("Before validating token");
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        System.out.println("After validating token");
    }*/

    public Boolean validateToken(String theToken){
        Jws<Claims> b = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(theToken);
        //final String userName = extractUsernameFromToken(theToken);
        final String userName = getSubject1(theToken);

        log.info("This Token : "+ theToken);
        log.info(userName);

        return ( !isTokenExpired(theToken));
    }



    public String getSubject1(String token)
    {
        return parsClaims(token).getSubject();
    }

    private Claims parsClaims(String token)
    {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()

                .parseClaimsJws(token)
                .getBody();

    }
    private boolean isTokenExpired(String theToken) {
        return extractExpirationTimeFromToken(theToken).before(new Date());
    }


    public Date extractExpirationTimeFromToken(String theToken) {
        return extractClaim(theToken, Claims :: getExpiration);
    }
}