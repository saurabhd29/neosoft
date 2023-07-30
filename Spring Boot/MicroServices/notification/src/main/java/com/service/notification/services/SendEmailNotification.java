package com.service.notification.services;

import com.service.notification.dtos.PublishEvent1;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SendEmailNotification {

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendEmailRegistration(PublishEvent1 publishEvent1){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("practice.saudhan@gmail.com");
        message.setTo(publishEvent1.getUserResponseBean().getEmail());
        message.setSubject("Registration Successfull "+ publishEvent1.getUserResponseBean().getName());
        message.setText("Welcome "+ publishEvent1.getUserResponseBean().getName()+
                "\nYour Registraion is Successful with below details.\n" +
                "\nUserName : "+ publishEvent1.getUserResponseBean().getUsername() +
                "\nEmail : "+ publishEvent1.getUserResponseBean().getEmail()+
                "\nCity : "+ publishEvent1.getUserResponseBean().getCity() +
                "\nStatus : "+ publishEvent1.getUserResponseBean().getStatus() +
                "\nThank You for Choosing Us!");
        javaMailSender.send(message);
        log.info("mail sent to {} : {}",publishEvent1.getUserResponseBean().getEmail(),message);
    }


    public void sendEmailLogin(PublishEvent1 publishEvent1){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("practice.saudhan@gmail.com");
        message.setTo(publishEvent1.getUserResponseBean().getEmail());
        message.setSubject("You have logged in to your account "+ publishEvent1.getUserResponseBean().getName());
        message.setText("Welcome "+ publishEvent1.getUserResponseBean().getName()+
                "\nYour Login is Successful with below details.\n" +
                "\nUserName : "+ publishEvent1.getUserResponseBean().getUsername() +
                "\nEmail : "+ publishEvent1.getUserResponseBean().getEmail()+
                "\nCity : "+ publishEvent1.getUserResponseBean().getCity() +
                "\nStatus : "+ publishEvent1.getUserResponseBean().getStatus() +
                "\nIf not done by you. Contact Shrijeet Ji for more assistance");
        javaMailSender.send(message);
        log.info("mail sent to {} : {}",publishEvent1.getUserResponseBean().getEmail(),message);
    }
    public void sendSMS(PublishEvent1 publishEvent1){
        Twilio.init("AC99773762d6bca29699bf9a5d14694ae1", "");

        Message.creator(new PhoneNumber("+919637426442"),
                new PhoneNumber(""),
                "Welcome "+ publishEvent1.getUserResponseBean().getName()+
                        "\nYour Registraion is Successful with below details.\n" +
                        "\nUserName : "+ publishEvent1.getUserResponseBean().getUsername() +
                        "\nEmail : "+ publishEvent1.getUserResponseBean().getEmail()+
                        "\nCity : "+ publishEvent1.getUserResponseBean().getCity() +
                        "\nStatus : "+ publishEvent1.getUserResponseBean().getStatus() +
                        "\nThank You for Choosing Us!").create();
        log.info("SMS Sent");
    }
}
