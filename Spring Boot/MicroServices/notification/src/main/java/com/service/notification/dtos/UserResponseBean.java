package com.service.notification.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseBean {
    private String username;
    private String name;
    private String email;
    private String mobileNo;
    private String city;
    private String roles;
    private String status;
    private String token;
    private Date createdAt;
}
