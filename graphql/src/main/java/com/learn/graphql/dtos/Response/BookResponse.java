package com.learn.graphql.dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {


    private String title;
    private String desc;

    private String author;
    private double price;
}
