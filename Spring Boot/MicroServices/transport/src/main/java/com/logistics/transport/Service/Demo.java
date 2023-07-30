package com.logistics.transport.Service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="DEMO-SERVICE")
public interface Demo {
    @GetMapping("/demo")
    public String test();
}
