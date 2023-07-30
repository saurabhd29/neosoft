package com.logistics.transport.Config;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.logistics.transport.Entites.Customer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, Customer> producerFactory(){
        Map<String,Object> config = new HashMap<String,Object>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"http://localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializable.class);
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean(name = "kafkaTemplate")
    public KafkaTemplate<String,Customer> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
