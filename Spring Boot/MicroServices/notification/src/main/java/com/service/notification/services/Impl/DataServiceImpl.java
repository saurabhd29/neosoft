package com.service.notification.services.Impl;

import com.service.notification.Repository.DataRepository;
import com.service.notification.dtos.PublishEvent1;
import com.service.notification.services.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Slf4j
@Service
public class DataServiceImpl implements DataService {
    @Autowired
    private DataRepository dataRepository;
    public void save(PublishEvent1 publishEvent1){
        publishEvent1.getUserResponseBean().setCreatedAt(Date.from(Instant.now()));
        dataRepository.save(publishEvent1);
        log.info("Saved inside MongoDB {}",publishEvent1);
    }
}
