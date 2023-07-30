package com.microservice.orderservice.dto.response;

import com.microservice.orderservice.entity.Order;
import com.microservice.orderservice.enums.OrderStatusType;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderStatus {

    private Order order;
    private OrderStatusType status;//progress,completed
    private String message;
}
