package com.programming.techie;

import com.programming.techie.dtos.OrderPlacedEvent;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceApplication {

    private final ObservationRegistry observationRegistry;
    private final Tracer tracer;

    @Autowired
    private JavaMailSender javaMailSender;

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
        Observation.createNotStarted("on-message", this.observationRegistry).observe(() -> {
            log.info("Got message <{}>", orderPlacedEvent);
            log.info("TraceId- {}, Received Notification for Order - {}", this.tracer.currentSpan().context().traceId(),
                    orderPlacedEvent.getOrderNumber());
        });
        // send out an email notification
        //sendEmailForNewOrder("saudhan29@gmail.com",orderPlacedEvent.getOrderNumber());
        //sendSMS(orderPlacedEvent.getOrderNumber() );
    }

    public void sendEmailForNewOrder(String email, String orderid ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("practice.saudhan@gmail.com");
        message.setTo(email);
        message.setSubject("Order No "+ orderid +" Placed Successfully !");
        message.setText("Your Order No "+ orderid +" with Rs " + "totalAmount" + " has been placed and it will be delivered within 30 min. Thank You for Choosing Us!");
        javaMailSender.send(message);
        log.info("mail sent : {}",message);
    }

    public void sendSMS(String orderid ){
        Twilio.init("", "");

        Message.creator(new PhoneNumber("+919637426442"),
                new PhoneNumber(""), "Hello your order with id "+ orderid +" placed successfully ").create();
        log.info("SMS Sent");
    }

}
