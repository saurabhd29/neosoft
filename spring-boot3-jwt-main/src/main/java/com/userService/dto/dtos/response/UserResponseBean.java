package com.userService.dto.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseBean implements Serializable {
    private String username;
    private Boolean Active;
    private String name;
    private String email;
    private String mobileNo;
    private String city;
    private String roles;
    private String status;
    private String token;

}
