package com.logistics.transport.Controller;

import com.logistics.transport.Service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/serviceprovider")
@RestController
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @GetMapping("/allserviceprovider")
    public ResponseEntity<?> getAllServProvider(){

        return ResponseEntity.ok(serviceProviderService.getAllServProvider());
    }

    @PostMapping("getcity/{city}")
    public ResponseEntity<?> getProvByCity(@PathVariable String city){

        return ResponseEntity.ok(serviceProviderService.getProvByCity(city));
    }
}
