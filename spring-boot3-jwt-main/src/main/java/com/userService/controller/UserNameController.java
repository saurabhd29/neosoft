package com.userService.controller;

import com.userService.entity.UserInfo;
import com.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/details")
public class UserNameController {

    @Autowired
    private UserService userService;

    @GetMapping("/username/{username}")//gateway
    public UserInfo getUserName(@PathVariable("username") String username){
        return userService.findByUser(username);
    }
}
