package com.logistics.transport.ServiceImpl;

import com.logistics.transport.Entites.Customer;
import com.logistics.transport.Repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PublisherServiceImpl {
    @Autowired
    KafkaTemplate<String,Customer> kafkaTemplate;
    @Autowired
    private CustomerRepository publisherRepository;
    public String add(Customer customer){
        //publisherRepository.save(customer);
        kafkaTemplate.send("jerry",customer);
        log.info("Kafka Send");
        return "Customer added to kafka queue successfully";
    }
}
