package com.order.notification.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    private String orderId;
    private String productId;
    private ProductType productType;
    private String name;
    private int qty;
    private double price;

    private Date createdAt;

    private Date updatedAt;
}

