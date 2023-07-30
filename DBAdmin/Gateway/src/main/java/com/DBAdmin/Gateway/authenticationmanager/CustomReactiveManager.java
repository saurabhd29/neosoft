package com.DBAdmin.Gateway.authenticationmanager;



import com.DBAdmin.Gateway.models.User;
import com.DBAdmin.Gateway.models.UserGatewayResponseBean;
import com.DBAdmin.Gateway.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomReactiveManager implements ReactiveAuthenticationManager {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getPrincipal().toString();
        String username= jwtUtil.getSubject(token);
        log.info(token);
        log.info("Username :: {}", username);
//        User user = restTemplate.getForObject("http://10.0.61.96:9002/user/get-by-id?email="+username,User.class);

//        log.info("User Details {}",user);
        if(jwtUtil.validateToken(token)){
            log.info("Inside UPAToken");
            return Mono.just(new UsernamePasswordAuthenticationToken(token,token,null));
        }
        return null;
    }
}
