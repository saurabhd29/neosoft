package com.service.notification;

import com.service.notification.dtos.PublishEvent1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class NotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

/*	@KafkaListener(topics = "userRegLogin1",groupId = "notification")
	public void consume(@Payload PublishEvent1 publishEvent){
		log.info("Recieved {}",publishEvent);
	}*/
}
