package com.adminService.admin.controllers;

import com.adminService.admin.dtos.AdminDto;
import com.adminService.admin.dtos.ApiResponse;
import com.adminService.admin.dtos.Credentials;
import com.adminService.admin.entites.Admin;
import com.adminService.admin.serviceImpl.Response;
import com.adminService.admin.services.AuthService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/admin")
public class    AdminController {


    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private AuthService authService;

    @GetMapping("/findall")
    @Cacheable(value = "findall")
    public Object findAll(){
        return authService.findAll();
    }


    @GetMapping("/username/{adminName}")//gateway
    public Admin getUserName(@PathVariable("adminName") String adminName){
        return authService.findByAdminName(adminName);
    }

    @GetMapping("/findbyusername/{adminName}")
    @Cacheable(value="adminName", key="#adminName")
    @CircuitBreaker(name="adminFindByUsernameBreaker" ,fallbackMethod="adminFindByUsernameBreaker" )
    public Object findByUsername(@PathVariable("adminName") String adminName){
        log.info("Inside findbyusername");
        //String token = restTemplate.getForObject("http://localhost:9002/user/name", String.class);

        AdminDto adminDto = authService.findByUsername(adminName);
        log.info("After rest : "+ adminDto);
        if(adminDto!=null){
            //return Response.success(adminDto);
            return adminDto;
        }
        //return Response.error(null);
        return null;
    }
    //fallback method for circuit breaker
    //return type of fallback should be same
    public ResponseEntity<?> adminFindByUsernameBreaker(String adminName,Exception ex)
    {
        log.info("Inside CR");
        ApiResponse apiResponse = ApiResponse.builder()
                .message("I am Circuit Breaker")
                .success(false)
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .build();
        return Response.error(apiResponse);
    }

    @GetMapping("/generatetoken/{adminName}")
    public String token(@PathVariable("adminName") String adminName){
        log.info("Inside Token");
        return authService.generateToken(adminName);
    }

    @GetMapping("/validatetoken/")
    public String validateToken(@RequestParam("token") String token){
        log.info("Inside Token");
        return authService.validateToken(token);
    }

    @GetMapping("/savetoken/{adminName}/{token}")
    public String savetoken(@PathVariable("adminName") String adminName,@PathVariable("token") String token){
        log.info("Inside savetoken");
        authService.saveToken(adminName,token);
        log.info("Saved"+token);
        return "Saved";
    }

    @GetMapping("/gettoken/{adminName}")
    public String gettoken(@PathVariable("adminName") String adminName){
        log.info("Inside gettoken");
        return authService.generateToken(adminName);
    }

    @PostMapping("/register")
    @CacheEvict(value = "findall",allEntries = true)
    public ResponseEntity<?> register(@RequestBody Admin data){
        log.info("Inside Register");
        AdminDto adminDto = authService.register(data);
        if(adminDto!=null){
            return Response.success(adminDto);
        }
        return Response.error(null);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials data){
        AdminDto adminDto = authService.login(data);
        log.info("hello");
        if(adminDto!=null){
            return Response.success(adminDto);
        }
        return Response.error(null);
    }
}
