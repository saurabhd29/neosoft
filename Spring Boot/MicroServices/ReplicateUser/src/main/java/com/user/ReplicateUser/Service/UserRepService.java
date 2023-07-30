package com.user.ReplicateUser.Service;

import com.user.ReplicateUser.dto.dtos.request.UserRequestBean;
import com.user.ReplicateUser.dto.dtos.response.ServiceResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;


public interface UserRepService {

    void saveUser(UserRequestBean userRequestBean);
    public ServiceResponseBean getAll();
}
