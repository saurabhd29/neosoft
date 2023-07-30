package com.userService.service;

import com.userService.dto.dtos.request.Credentials;
import com.userService.dto.dtos.request.UserRequestBean;
import com.userService.dto.dtos.response.ServiceResponseBean;
import com.userService.entity.UserInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserService {

    public ServiceResponseBean register(UserRequestBean userRequestBean);

    public ServiceResponseBean login(Credentials credentials);

    public ServiceResponseBean getAll();

    public ServiceResponseBean getReplicationAll();

    public UserInfo findByUser(String username);

    public ServiceResponseBean findByUserName(String username);

    public ServiceResponseBean update(UserRequestBean userRequestBean);

    public ServiceResponseBean delete(String username);

}
