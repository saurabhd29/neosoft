package com.DBAdmin.Gateway.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data

@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable {

    private String username;
    private String name;
    private String email;
    private String password;
    private String city;
    private String roles;
    private String status;
    private String token;

}
