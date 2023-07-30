package com.service.notification.Repository;

import com.service.notification.dtos.PublishEvent1;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends MongoRepository<PublishEvent1,String> {
}
