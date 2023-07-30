package com.microservice.orderservice.dto.response;

import com.microservice.orderservice.dto.request.OrderRequestBean;
import lombok.*;
import org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiceResponseBean {

    private OrderResponseBean order;
    private Object data;
    private String orderStatus;//progress,completed
    private String message;
    private Boolean status;


}
