package com.logistics.transport.Controller;

import com.logistics.transport.Entites.Customer;
import com.logistics.transport.Service.PublisherService;
import com.logistics.transport.Service.TransportUserService;
import com.logistics.transport.ServiceImpl.PublisherServiceImpl;
import com.logistics.transport.ServiceImpl.Response;
import com.logistics.transport.ServiceImpl.TransportUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/publisher")
@RestController
public class PublisherController {

    @Autowired
    private PublisherServiceImpl publisherService;
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Customer customer){
        log.info("Inside add");
        publisherService.add(customer);
        return Response.success(null);
    }
}
