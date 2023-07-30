package com.microservice.orderservice.controller;

import com.microservice.orderservice.dto.request.OrderRequestBean;
import com.microservice.orderservice.dto.response.ServiceResponseBean;
import com.microservice.orderservice.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/place-order")
    public ServiceResponseBean placeOrder(@RequestBody OrderRequestBean orderRequestBean){
        return orderService.placeOrder(orderRequestBean);
    }
}
