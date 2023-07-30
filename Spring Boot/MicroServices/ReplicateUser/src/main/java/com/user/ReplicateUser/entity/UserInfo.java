package com.user.ReplicateUser.entity;

import jakarta.persistence.Entity;
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


    private String username;
    private String name;


    private String mobileNo;

    private String email;
    private String password;
    private String city;
    private String roles;
    private String status;
    private String token;
}
