/*
package com.logistics.transport.Jwt;

import com.logistics.transport.Entites.TransportDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.ObjectUtils;
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }

    private boolean hasAuthorizationBearer(HttpServletRequest request)
    {
        String header = request.getHeader("Authorization");

        if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer"))
            return false;

        return true;
    }

    private String getAccessToken(HttpServletRequest request)
    {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }

    public void setAuthenticationContext(String token, HttpServletRequest request)
    {
        UserDetails userDetails = getUserDetails(token);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,null);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    public UserDetails getUserDetails(String token)
    {
        TransportDetails userDetails = new TransportDetails();

        String[] jwtSubject = jwtTokenHelper.getSubject(token).split(",");

        userDetails.setTransporterId(Integer.parseInt(jwtSubject[0]));
        userDetails.setEmail(jwtSubject[1]);

        return (UserDetails) userDetails;
    }
}
*/
