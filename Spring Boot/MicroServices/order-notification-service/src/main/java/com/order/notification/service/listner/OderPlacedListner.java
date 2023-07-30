package com.order.notification.service.listner;


import com.order.notification.service.config.RabbitMqConfig;
import com.order.notification.service.dto.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OderPlacedListner {

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void orderPlaced(OrderStatus orderStatus) {
        log.info("OrderStatus {} ",orderStatus);
    }

}
