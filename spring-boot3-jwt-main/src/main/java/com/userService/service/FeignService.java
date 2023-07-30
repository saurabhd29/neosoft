package com.userService.service;

import com.userService.dto.dtos.request.UserRequestBean;
import com.userService.dto.dtos.response.ServiceResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "userReplication-service")
public interface FeignService {

    @PostMapping("/replication/save")
    public void saveUser(@RequestBody UserRequestBean userRequestBean);

    @GetMapping("/replication/getAll")
    public ServiceResponseBean getAll();

}