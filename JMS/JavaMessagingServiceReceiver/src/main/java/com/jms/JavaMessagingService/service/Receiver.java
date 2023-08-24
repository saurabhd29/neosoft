package com.jms.JavaMessagingService.service;

import com.jms.JavaMessagingService.beans.Email;
import com.jms.JavaMessagingService.beans.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receiver {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        System.out.println("Received <" + email + ">");
    }


    @JmsListener(destination = "abc", containerFactory = "myFactory")
    public void receiveEmployeeMessage(Employee emp) {
       log.info("Msg recieved :: {}",emp);
    }

}
