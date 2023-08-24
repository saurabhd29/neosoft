package com.jms.JavaMessagingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJms
@EnableScheduling
public class JavaMessagingServiceApplication {


	public static void main(String[] args) {

		SpringApplication.run(JavaMessagingServiceApplication.class, args);

	}



}
