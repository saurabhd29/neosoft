package com.jms.JavaMessagingService.service;

import com.jms.JavaMessagingService.beans.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;
    int count=0;

    @Scheduled(cron = "0 49 14 * * ?")
    public void send(){
        count++;
        log.info("Email send :: {} ",count);
        jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", count));
    }


    // Send a message with a POJO - the template reuse the message converter

}
