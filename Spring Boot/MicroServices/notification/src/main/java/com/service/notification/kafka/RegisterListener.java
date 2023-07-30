package com.service.notification.kafka;

//import com.userService.dto.PublishEvent;
import com.service.notification.dtos.PublishEvent1;
import com.service.notification.services.DataService;
import com.service.notification.services.SendEmailNotification;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RegisterListener {
    @Autowired
    private SendEmailNotification sendEmailNotification;

    @Autowired
    private DataService dataService;

    public static final Logger log = LoggerFactory.getLogger(RegisterListener.class);

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;
    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${spring.kafka.consumer.group-id}",
                    topicPartitions = @TopicPartition(topic = "${spring.kafka.template.default-topic}",
                            partitionOffsets = {@PartitionOffset(partition = "0", initialOffset = "10")}))
    public void consume(@Payload PublishEvent1 publishEvent){
        sendEmailNotification.sendEmailRegistration(publishEvent);
        //sendEmailNotification.sendSMS(publishEvent);

        log.info("Recieved Register {}",publishEvent);
        dataService.save(publishEvent);
    }

}
