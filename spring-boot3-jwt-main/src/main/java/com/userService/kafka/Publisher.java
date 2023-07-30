package com.userService.kafka;

import com.userService.dto.dtos.response.UserResponseBean;
import com.userService.event.PublishEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    public static final Logger log = LoggerFactory.getLogger(Publisher.class);
    @Autowired
    private NewTopic topic;

    private KafkaTemplate<String, PublishEvent> kafkaTemplate;

    public Publisher(KafkaTemplate<String, PublishEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(UserResponseBean userResponseBean,String message, String type) {

        log.info("UserResponseBean : " + userResponseBean);
        int partition=0;
        if(type.equals("Login")){
            partition =1;
        }
        PublishEvent publishEvent = PublishEvent
                .builder()
                .message(message)
                .status("Completed")
                .userResponseBean(userResponseBean)
                .build();

        Message<PublishEvent> msg = MessageBuilder
                .withPayload(publishEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
         //kafkaTemplate.send(msg);
        //kafkaTemplate.send(topic.name(), orderid,orderEvent);
        log.info("Partion {}",partition);
        log.info("Partion {}",partition);
        kafkaTemplate.send(topic.name(), partition, userResponseBean.getUsername(), publishEvent);

    }
}
