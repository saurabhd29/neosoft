package com.microservice.orderservice.repository;

import com.microservice.orderservice.entity.Order;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {
}
