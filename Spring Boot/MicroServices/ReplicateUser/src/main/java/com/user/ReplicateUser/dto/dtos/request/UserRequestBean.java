package com.user.ReplicateUser.dto.dtos.request;



import com.user.ReplicateUser.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestBean extends BaseEntity {

    private String username;
    private String name;
    private String mobileNo;
    private String email;
    private String password;
    private String city;
    private String roles;
    private String status;

}
