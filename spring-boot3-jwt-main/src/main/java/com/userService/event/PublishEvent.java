package com.userService.event;

import com.userService.dto.dtos.response.UserResponseBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

import java.io.Serializable;

@Data

@Builder
public class PublishEvent extends ApplicationEvent implements Serializable {
    private String message;
    private String status;
    private UserResponseBean userResponseBean;

    public PublishEvent(Object source) {
        super(source);
    }
    public PublishEvent(Object source,String message,String status,UserResponseBean userResponseBean) {
        super(source);
        this.message=message;
        this.status=status;
        this.userResponseBean=userResponseBean;
    }
    public PublishEvent(String message,String status,UserResponseBean userResponseBean) {
        super(userResponseBean.getUsername());
        this.message=message;
        this.status=status;
        this.userResponseBean=userResponseBean;
    }
}
