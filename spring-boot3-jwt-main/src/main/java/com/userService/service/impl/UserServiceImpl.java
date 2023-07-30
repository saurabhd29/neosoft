package com.userService.service.impl;

import com.userService.dto.dtos.request.Credentials;
import com.userService.dto.dtos.request.UserRequestBean;
import com.userService.dto.dtos.response.ServiceResponseBean;
import com.userService.dto.dtos.response.UserResponseBean;
import com.userService.entity.UserInfo;
import com.userService.exceptions.DataAlreadyExistsException;
import com.userService.kafka.Publisher;
import com.userService.repository.UserInfoRepository;
import com.userService.service.FeignService;
import com.userService.service.JwtService;
import com.userService.service.UserService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private Publisher publisher;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private FeignService feignService;

    @Transactional
    public ServiceResponseBean register(UserRequestBean userRequestBean){
        Optional<UserInfo> userInfo = this.userInfoRepository.findByUsername(userRequestBean.getUsername());
        if(userInfo.isEmpty()){
            userRequestBean.setPassword(encoder.encode(userRequestBean.getPassword()));
            userRequestBean.setStatus("ACTIVE");

            userRequestBean.setCreatedAt(Date.from(Instant.now()));
            UserInfo userSaved = this.userInfoRepository.saveAndFlush(this.modelMapper.map(userRequestBean,UserInfo.class));
            log.info("User Saved {} ", userSaved);
            UserResponseBean userResponseBean = this.modelMapper.map(userSaved, UserResponseBean.class);
            feignService.saveUser(userRequestBean);
            publisher.sendMessage(userResponseBean,"User registered Successfully","Register");
            return ServiceResponseBean.builder()
                    .status(Boolean.TRUE)
                    //.data(this.modelMapper.map(userSaved, UserResponseBean.class))
                    .data(userResponseBean)
                    .message("User Saved Successfully")
                    .build();
        }
        log.info("User Exists {} ", userRequestBean.getUsername());
        return ServiceResponseBean.builder()
                .status(Boolean.FALSE)
                .message(new DataAlreadyExistsException("Data Already Exists").getMessage())

                .build();
    }

    public ServiceResponseBean login(Credentials credentials){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        Boolean b = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        log.info("isAuthenticated : {} ",b  );
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(credentials.getUsername());
            Optional<UserInfo> userInfo = this.userInfoRepository.findByUsername(credentials.getUsername());
            userInfo.get().setToken(token);
            UserInfo userSaved = userInfoRepository.save(userInfo.get());
            UserResponseBean userResponseBean = this.modelMapper.map(userSaved, UserResponseBean.class);
            publisher.sendMessage(userResponseBean,"User login Successfully","Login");
            return ServiceResponseBean.builder()
                    .status(Boolean.TRUE)
                    .data(userResponseBean)
                    .message("Login success")
                    .build();
        }
        publisher.sendMessage(null,"User login Failed","Login");
        return ServiceResponseBean.builder()
                .status(Boolean.FALSE)
                .message("Failed")
                .build();
    }

    public ServiceResponseBean getAll(){
        Optional<List<UserInfo>> optionalUserInfos = Optional.of(this.userInfoRepository.findAll());

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
                .message("List found")
                .status(Boolean.TRUE)
                .data(userResponseBeanList)
                .build();
    }

    public ServiceResponseBean getReplicationAll(){
        return feignService.getAll();
    }
    public ServiceResponseBean update(UserRequestBean userRequestBean){
        Optional<UserInfo> userInfo = this.userInfoRepository.findByUsername(userRequestBean.getUsername());
        if(userInfo.get().getActive()){
            userInfo.get().setStatus(userRequestBean.getStatus());
            userInfo.get().setCity(userRequestBean.getCity());
            userInfo.get().setEmail(userRequestBean.getEmail());
            userInfo.get().setName(userRequestBean.getName());

            UserInfo userUpdated = this.userInfoRepository.saveAndFlush(this.modelMapper.map(userInfo.get(),UserInfo.class));
            log.info("User Updated {} ", userUpdated);
            return ServiceResponseBean.builder()
                    .status(Boolean.TRUE)
                    .data(this.modelMapper.map(userUpdated, UserResponseBean.class))
                    .message("User Updated Successfully")
                    .build();
        }
        log.info("User NOT Updated {} ", userRequestBean);
        return ServiceResponseBean.builder()
                .status(Boolean.FALSE)
                .message(new DataAlreadyExistsException("User Does NOT Exists").getMessage())

                .build();
    }


    public ServiceResponseBean findByUserName(String username){
        log.info("Inside findByUserName ");
        Optional<UserInfo> userInfo = this.userInfoRepository.findByUsername(username);
        if(userInfo.get().getActive()){
            log.info("User Found {} ", userInfo);
            log.info("User Active {} ", userInfo.get().getActive());
            return ServiceResponseBean.builder()
                    .status(Boolean.TRUE)
                    .data(this.modelMapper.map(userInfo.get(), UserResponseBean.class))
                    .message("User Found")
                    .build();
        }
        log.info("User NOT Found {} ", username);
        return ServiceResponseBean.builder()
                .status(Boolean.FALSE)
                .message(new DataAlreadyExistsException("User NOT Found ").getMessage())

                .build();
    }

    public ServiceResponseBean delete(String username){
        Optional<UserInfo> userInfo = this.userInfoRepository.findByUsername(username);
        if(userInfo.get().getActive()){
            log.info("User Deleted {} ", userInfo);
            userInfo.get().setActive(Boolean.FALSE);
            userInfo.get().setToken(null);
            UserInfo userDel = this.userInfoRepository.saveAndFlush(userInfo.get());
            return ServiceResponseBean.builder()
                    .status(Boolean.TRUE)
                    .data(this.modelMapper.map(userDel, UserResponseBean.class))
                    .message("User Deleted")
                    .build();
        }
        log.info("User NOT Found for deletion {} ", username);
        return ServiceResponseBean.builder()
                .status(Boolean.FALSE)
                .message(new DataAlreadyExistsException("User NOT Found for deletion "+username).getMessage())

                .build();
    }

    public UserInfo findByUser(String username){
        Optional<UserInfo> userInfo = this.userInfoRepository.findByUsername(username);
        if(userInfo.isEmpty()){
            return null;
        }
        if(userInfo.get().getActive().equals(Boolean.FALSE))
        {
            userInfo.get().setUsername(null);
        }
        return userInfo.get();
    }


}
