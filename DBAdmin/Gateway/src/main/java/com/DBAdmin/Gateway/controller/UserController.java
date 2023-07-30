package com.DBAdmin.Gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/gateway")
public class UserController {

    @GetMapping("/demo")
    public String demo(){
        return "demo";
    }

}
