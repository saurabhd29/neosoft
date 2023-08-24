package com.jms.JavaMessagingService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class JavaMessagingServiceApplicationReceiver {

	public static void main(String[] args) {
		SpringApplication.run(JavaMessagingServiceApplicationReceiver.class, args);
	}

}
