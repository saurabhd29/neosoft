package com.microservice.orderservice.dto.response;

import com.microservice.orderservice.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponseBean {

    private String orderId;
    private String productId;
    private ProductType productType;
    private String name;
    private int qty;
    private double price;
}

