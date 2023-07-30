package com.service.notification.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishEvent1 implements Serializable {
    private String message;
    private String status;
    private UserResponseBean userResponseBean;
}
