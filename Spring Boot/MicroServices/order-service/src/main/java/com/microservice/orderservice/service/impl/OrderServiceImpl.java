package com.microservice.orderservice.service.impl;

import com.microservice.orderservice.config.RabbitMqConfig;
import com.microservice.orderservice.dto.request.InventoryRequestBean;
import com.microservice.orderservice.dto.request.OrderRequestBean;
import com.microservice.orderservice.dto.response.OrderResponseBean;
import com.microservice.orderservice.dto.response.OrderStatus;
import com.microservice.orderservice.dto.response.ServiceResponseBean;
import com.microservice.orderservice.entity.Order;
import com.microservice.orderservice.enums.OrderStatusType;
import com.microservice.orderservice.enums.ProductType;
import com.microservice.orderservice.repository.OrderRepository;
import com.microservice.orderservice.service.BooksFeignService;
import com.microservice.orderservice.service.FeignService;
import com.microservice.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FeignService feignService;

    @Autowired
    private BooksFeignService booksFeignService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ServiceResponseBean placeOrder(OrderRequestBean orderRequestBean) {

        if (orderRequestBean.getProductType().equals(ProductType.HDD) && feignService.inventoryCheck(InventoryRequestBean.builder().productId(orderRequestBean.getProductId()).qty(orderRequestBean.getQty()).build())) {
            orderRequestBean.setOrderId(UUID.randomUUID().toString());
            Order order = this.orderRepository.save(this.modelMapper.map(orderRequestBean, Order.class));
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,RabbitMqConfig.ROUTING_KEY, OrderStatus.builder().status(OrderStatusType.ORDER_PLACED).message("Success").order(order).build());
            return ServiceResponseBean.builder()
                    .order(this.modelMapper.map(order, OrderResponseBean.class))
                    .orderStatus("Order Placed...")
                    .message("Order Placed Successfully")
                    .status(Boolean.TRUE)
                    .build();
        } else if (orderRequestBean.getProductType().equals(ProductType.BOOKS) && booksFeignService.inventoryCheck(InventoryRequestBean.builder().productId(orderRequestBean.getProductId()).qty(orderRequestBean.getQty()).build())) {
            log.info("inside Books");
            orderRequestBean.setOrderId(UUID.randomUUID().toString());
            Order order = this.orderRepository.save(this.modelMapper.map(orderRequestBean, Order.class));
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,RabbitMqConfig.ROUTING_KEY,OrderStatus.builder().status(OrderStatusType.ORDER_PLACED).message("Success").order(order).build());
            return ServiceResponseBean.builder()
                    .order(this.modelMapper.map(order, OrderResponseBean.class))
                    .orderStatus("Order Placed...")
                    .message("Order Placed Successfully")
                    .status(Boolean.TRUE)
                    .build();
        }
        return ServiceResponseBean.builder()
                .orderStatus("Order Not Placed")
                .message("Try Again")
                .status(Boolean.FALSE)
                .build();
    }
}
