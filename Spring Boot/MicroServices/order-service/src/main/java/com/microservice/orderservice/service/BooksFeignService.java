package com.microservice.orderservice.service;

import com.microservice.orderservice.dto.request.InventoryRequestBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "book-service")
public interface BooksFeignService {

    @PostMapping("/v1/api/books/inventoryCheck")
    public Boolean inventoryCheck(@RequestBody InventoryRequestBean inventoryBean);
}
