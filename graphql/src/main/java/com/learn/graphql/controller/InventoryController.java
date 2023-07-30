package com.learn.graphql.controller;


import com.learn.graphql.dtos.Request.InventoryRequestBean;
import com.learn.graphql.service.impl.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/books")
public class InventoryController {
    @Autowired
    private InventoryServiceImpl inventoryService;
    @PostMapping("/inventoryCheck")
    public Boolean inventoryCheck(@RequestBody InventoryRequestBean inventoryBean){
        return inventoryService.inventoryCheck(inventoryBean);
    }
}
