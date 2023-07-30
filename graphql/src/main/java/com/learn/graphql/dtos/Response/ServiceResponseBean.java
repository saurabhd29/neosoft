package com.learn.graphql.dtos.Response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceResponseBean {


    private String orderStatus;//progress,completed
    private String message;
    private Boolean status;


}
