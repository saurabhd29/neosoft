package com.user.ReplicateUser.dto.dtos.response;


import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Response1 {

    public static ResponseEntity<?> success(Object data){

        Map<String,Object> map = new HashMap<>();
        map.put("data",data);
        map.put("status","success");
        return ResponseEntity.ok(map);
    }
}
