package com.user.ReplicateUser.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class cofig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
