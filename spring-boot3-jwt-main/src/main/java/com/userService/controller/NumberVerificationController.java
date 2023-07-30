package com.userService.controller;

import com.userService.record.NumberRecord;
import com.userService.service.NumberVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/verify")
public class NumberVerificationController {

    @Autowired
    private NumberVerificationService verificationService;
    @GetMapping("/mobile-number")
    public ResponseEntity<?> validateNumber(@RequestParam("number") NumberRecord request) throws IOException {
        return ResponseEntity.ok(verificationService.verification(request.number()));
    }
}
