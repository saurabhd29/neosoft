package com.jms.JavaMessagingService.controller;

import com.jms.JavaMessagingService.beans.Email;
import com.jms.JavaMessagingService.beans.Employee;
import com.jms.JavaMessagingService.service.Sender;
import jakarta.jms.Queue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PublisherController {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Sender sender;

    @GetMapping("/send")
    public void send(){
        log.info("Msg sent :: {}");
        sender.send();
        jmsTemplate.convertAndSend("mailbox", new Email("Srijeet@example.com", "Hello"));
    }

    @PostMapping("/sendEmployee")
    public void sendEmployee(@RequestBody Employee employee){
        log.info("Msg sent :: {}",employee);

        jmsTemplate.convertAndSend("abc", employee);
    }
}
