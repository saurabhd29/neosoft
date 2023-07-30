package com.DBAdmin.Gateway.authenticationmanager;

import com.DBAdmin.Gateway.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomSecurityContext implements ServerSecurityContextRepository {

    @Autowired
    private CustomReactiveManager reactiveManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new UnsupportedOperationException("...");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        log.info("Inside security context");
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(authHeader -> authHeader.startsWith("Bearer "))
                .flatMap(authHeader -> {
                    //String token = authHeader.substring(7);
                    String token = authHeader.replace("Bearer ","");
                    log.info("T1 :"+token);
                    if(token==null){
                        throw new ResourceNotFoundException("Invalid token");
                    }
                    Authentication auth = new UsernamePasswordAuthenticationToken(token, token,null);
                    log.info("Status "+String.valueOf(auth.isAuthenticated()));
                    return this.reactiveManager.authenticate(auth).map(SecurityContextImpl::new);
                });
    }
}
