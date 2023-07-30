package com.logistics.transport.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="PACKING-SERVICE")
public interface ServiceProviderService {

    @GetMapping("AllServiceProvider")
    public ResponseEntity<?> getAllServProvider();


    @PostMapping("getCity/{city}")
    public ResponseEntity<?> getProvByCity(@PathVariable String city);

}
