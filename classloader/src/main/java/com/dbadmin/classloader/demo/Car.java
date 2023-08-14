package com.dbadmin.classloader.demo;

import org.springframework.stereotype.Component;

@Component
public class Car {

    public String getCarName(){
        System.out.println("MyCar");
        return "MyCar";
    }
}
