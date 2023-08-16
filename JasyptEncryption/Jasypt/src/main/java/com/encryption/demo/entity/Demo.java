package com.encryption.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Demo {
    @Id
    private int id;

    private String name;
}
