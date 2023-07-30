package com.service.notification.kafka;


import com.service.notification.dtos.PublishEvent1;
import com.service.notification.services.DataService;
import com.service.notification.services.SendEmailNotification;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginListener {

    @Autowired
    private DataService dataService;

    @Autowired
    private SendEmailNotification sendEmailNotification;
    public static final Logger log = LoggerFactory.getLogger(RegisterListener.class);

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;
    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${spring.kafka.consumer.group-id}",
            topicPartitions = @TopicPartition(topic = "${spring.kafka.template.default-topic}",
                    partitionOffsets = {@PartitionOffset(partition = "1",initialOffset = "4")}))
    public void consume(PublishEvent1 publishEvent){
        sendEmailNotification.sendEmailLogin(publishEvent);
        log.info("Recieved Login {}",publishEvent);
        dataService.save(publishEvent);
    }

}
