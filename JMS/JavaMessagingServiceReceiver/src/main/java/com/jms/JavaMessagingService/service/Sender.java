package com.jms.JavaMessagingService.service;

import com.jms.JavaMessagingService.beans.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(){
        System.out.println("Sending an email message.");
        jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello"));
    }


    // Send a message with a POJO - the template reuse the message converter

}
