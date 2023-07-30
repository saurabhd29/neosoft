package com.microservice.orderservice.service;


import com.microservice.orderservice.dto.request.InventoryRequestBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "HardDrive-service")
public interface FeignService {

    @PostMapping("/v1/api/hard-drive/inventoryCheck")
    public Boolean inventoryCheck(@RequestBody InventoryRequestBean inventoryBean);
}
