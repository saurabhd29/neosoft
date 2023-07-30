package com.microservice.orderservice.service;

import com.microservice.orderservice.dto.request.OrderRequestBean;
import com.microservice.orderservice.dto.response.ServiceResponseBean;

public interface OrderService {

    public ServiceResponseBean placeOrder(OrderRequestBean orderRequestBean);
}
