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
@RequestMapping("/encryption")
public class EncryptController {
    @Autowired
    private StringEncryptor encryptor;

    @Autowired
    private CustomPasswordEncryptor customPasswordEncryptor;

    @GetMapping("/encrypt-data")
    public String enc(@RequestParam String data){
        log.info("Data :: {}",data);
        String encryptData = encryptor.encrypt(data);
        log.info("Enc Data :: {}",encryptData);
        return encryptData;
    }

    @GetMapping("/encrypt-with-custom-key")
    public String enc(@RequestParam String data, @RequestParam String secretKey){
        log.info("Data :: {}",data);
        StringEncryptor stringEncryptor = customPasswordEncryptor.customPasswordEncryptor(secretKey);
        String encryptData = stringEncryptor.encrypt(data);
        log.info("Enc Data :: {}",encryptData);
        return encryptData;
    }



}
