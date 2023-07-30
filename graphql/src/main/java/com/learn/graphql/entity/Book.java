package com.learn.graphql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.io.Serializable;

@Entity
@Data
@Audited
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity{

    /*@Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private long id;*/

    private String title;
    private String description;

    private String author;
    private double price;
    private int qty;
}
