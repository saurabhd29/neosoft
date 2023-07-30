package com.userService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final Logger LOGGER = LoggerFactory.getLogger(KafkaTopicConfig.class);
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Bean
    public NewTopic newJsonTopic(){
        LOGGER.info(topicName);
        return TopicBuilder.name(topicName).partitions(2)
                .build();
    }

}
