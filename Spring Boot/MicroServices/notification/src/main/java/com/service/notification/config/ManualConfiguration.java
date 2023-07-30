package com.service.notification.config;

import com.service.notification.dtos.PublishEvent1;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

/**
 * In this class we'll add all the manual configuration required for Observability to
 * work.
 */
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class ManualConfiguration {

    private final ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory;
    /*@Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }*/

    @PostConstruct
    void setup() {
        this.concurrentKafkaListenerContainerFactory.getContainerProperties().setObservationEnabled(true);
    }

}
