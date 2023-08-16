package com.encryption.demo.controller;

import com.encryption.demo.config.CustomPasswordEncryptor;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/decryption")
public class DecryptController {
    @Autowired
    private StringEncryptor encryptor;

    @Autowired
    private CustomPasswordEncryptor customPasswordEncryptor;
    @GetMapping("/decrypt")
    public String dec(@RequestParam String encryptData){
        log.info("encryptData :: {}",encryptData);
        String decryptData = encryptor.decrypt(encryptData);
        log.info("Enc Data :: {}",decryptData);
        return decryptData;
    }

    @GetMapping("/encrypt-with-custom-key")
    public String enc(@RequestParam String encryptData, @RequestParam String secretKey){
        log.info("encryptData :: {}",encryptData);
        StringEncryptor stringEncryptor = customPasswordEncryptor.customPasswordEncryptor(secretKey);
        String decryptData = stringEncryptor.decrypt(encryptData);
        log.info("decryptData Data :: {}",decryptData);
        return decryptData;
    }
}
