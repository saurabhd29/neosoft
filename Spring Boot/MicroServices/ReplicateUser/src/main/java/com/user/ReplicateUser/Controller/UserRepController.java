package com.user.ReplicateUser.Controller;

import com.user.ReplicateUser.Service.UserRepService;
import com.user.ReplicateUser.dto.dtos.request.UserRequestBean;
import com.user.ReplicateUser.dto.dtos.response.ServiceResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/replication")
public class UserRepController {

    @Autowired
    private UserRepService userRepService;
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @PostMapping("/save")
    public void saveUser(@RequestBody UserRequestBean userRequestBean){
        log.info("Save {}",userRequestBean);
        userRepService.saveUser(userRequestBean);
    }

    @GetMapping("/getAll")
    public ServiceResponseBean getAll(){
        log.info("Inside Get");
        return userRepService.getAll();
    }
}
