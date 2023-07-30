package com.logistics.transport.Controller;

import com.logistics.transport.Service.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private Demo demo;

    @GetMapping("/test")
    public ResponseEntity<?> test(){

        return ResponseEntity.ok(demo.test());
    }
}
