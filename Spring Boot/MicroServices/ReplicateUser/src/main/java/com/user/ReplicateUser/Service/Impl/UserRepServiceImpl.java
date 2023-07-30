package com.user.ReplicateUser.Service.Impl;

import com.user.ReplicateUser.Service.UserRepService;
import com.user.ReplicateUser.dto.dtos.request.UserRequestBean;
import com.user.ReplicateUser.dto.dtos.response.ServiceResponseBean;
import com.user.ReplicateUser.dto.dtos.response.UserResponseBean;
import com.user.ReplicateUser.entity.UserInfo;
import com.user.ReplicateUser.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserRepServiceImpl implements UserRepService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    public void saveUser(UserRequestBean userRequestBean) {

        UserInfo userSaved = this.userRepository.saveAndFlush(this.modelMapper.map(userRequestBean,UserInfo.class));
        log.info("User Saved {} ", userSaved);

    }

    public ServiceResponseBean getAll(){
        Optional<List<UserInfo>> optionalUserInfos = Optional.of(this.userRepository.findAll());

        List<UserInfo> userInfoList = optionalUserInfos.get();

        if(userInfoList.isEmpty()){
            return ServiceResponseBean.builder()
                    .message("List Empty")
                    .status(Boolean.FALSE)
                    .build();
        }//.filter(user.getActive().equals(true)
        List<UserResponseBean> userResponseBeanList = userInfoList.stream()
                .filter(userInfo -> userInfo.getActive().equals(Boolean.TRUE))
                .map(user -> this.modelMapper.map(user, UserResponseBean.class))

                .collect(Collectors.toList());
        log.info("User List : {} ",userResponseBeanList);
        return ServiceResponseBean.builder()
                .message("Replication List")
                .status(Boolean.TRUE)
                .data(userResponseBeanList)
                .build();
    }
}
