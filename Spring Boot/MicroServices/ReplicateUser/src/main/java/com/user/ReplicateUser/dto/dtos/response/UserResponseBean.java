package com.user.ReplicateUser.dto.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
