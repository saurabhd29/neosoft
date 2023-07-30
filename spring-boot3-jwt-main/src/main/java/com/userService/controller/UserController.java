package com.userService.controller;


import com.userService.config.UserInfoUserDetailsService;
import com.userService.dto.ApiResponse;
import com.userService.dto.AuthRequest;
import com.userService.dto.dtos.request.Credentials;
import com.userService.dto.dtos.request.UserRequestBean;
import com.userService.dto.dtos.response.ServiceResponseBean;
import com.userService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    Logger log = LoggerFactory.getLogger(UserController.class);

    @CacheEvict(value = "findall1", allEntries = true)
    @CircuitBreaker(name="registerCircuitBreaker" ,fallbackMethod="registerCircuitBreaker")
    @PostMapping("/register")
    public ServiceResponseBean register(@RequestBody UserRequestBean userRequestBean){
        return userService.register(userRequestBean);
    }

    public ServiceResponseBean registerCircuitBreaker(UserRequestBean userRequestBean, Throwable t)
    {
        log.info("Inside CR");
        ApiResponse apiResponse = ApiResponse.builder()
                .message("I am Circuit Breaker")
                .success(false)
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .build();
        return ServiceResponseBean.builder()
                .message(t.getMessage())
                .data(apiResponse)
                .status(Boolean.FALSE)
                .build();
    }

    @Cacheable(value = "findall1")
    @CircuitBreaker(name="findallBreaker" ,fallbackMethod="findallBreaker" )
    @PreAuthorize("hasAuthority('ROLE_ADMIN')" )
    @GetMapping("/findall")
    public ServiceResponseBean findall(){
        log.info("Inside FindAll");
        return userService.getAll();
        //return userService.getReplicationAll();
    }

    public ServiceResponseBean findallBreaker(Throwable t)
    {
        log.info("Inside CR");
        ApiResponse apiResponse = ApiResponse.builder()
                .message("I am Circuit Breaker")
                .success(false)
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .build();
        return ServiceResponseBean.builder()
                .message(t.getMessage())
                .status(Boolean.FALSE)
                .build();
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')" )
    @DeleteMapping("/delete-by-username/{username}")
    public ServiceResponseBean delete(@PathVariable("username") String username){
        log.info("inside delete");
        return userService.delete(username);
    }
    @CachePut(value = "username",key = "#userRequestBean.getUsername()")
    @PutMapping("/update")
    public ServiceResponseBean update(@RequestBody UserRequestBean userRequestBean){
        log.info("inside put");

        return userService.update(userRequestBean);
    }

    @Cacheable(value = "username",key = "#username")
    @PreAuthorize("hasAuthority('ROLE_USER') ||  hasAuthority('ROLE_ADMIN')" )
    @GetMapping("/username/{username}")
    public ServiceResponseBean getUsername(@PathVariable("username") String username){

        return userService.findByUserName(username);
    }

    @PostMapping("/login")
    public ServiceResponseBean login(@RequestBody Credentials credentials) {

        return userService.login(credentials);
    }

    @GetMapping("/user/name")
    public String getName(){
        return "Name";
    }



}
