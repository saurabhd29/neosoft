package com.userService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.io.Serializable;

@Entity
@Data
@Audited
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo extends BaseEntity implements Serializable {

    @Column(unique=true)
    private String username;
    private String name;

    @Column(name = "mobileNo", unique=false)
    private String mobileNo;

    private String email;
    private String password;
    private String city;
    private String roles;
    private String status;
    private String token;
}
