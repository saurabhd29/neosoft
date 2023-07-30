package com.learn.graphql.dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private String title;
    private String desc;

    private String author;
    private double price;
}
