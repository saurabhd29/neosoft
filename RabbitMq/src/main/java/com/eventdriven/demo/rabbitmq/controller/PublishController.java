package com.eventdriven.demo.rabbitmq.controller;

import com.eventdriven.demo.rabbitmq.config.RabbitMqConfig;
import com.eventdriven.demo.rabbitmq.dto.Order;
import com.eventdriven.demo.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/rabbit")
public class PublishController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/order/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable("restaurantName") String restaurantName){
        order.setOrderId(UUID.randomUUID().toString());

        OrderStatus orderStatus = new OrderStatus(order,"Process","Order Placed successfully in "+ restaurantName);
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTING_KEY,orderStatus);
        return "Success";
    }
}
