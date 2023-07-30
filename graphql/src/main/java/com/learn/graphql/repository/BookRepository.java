package com.learn.graphql.repository;

import com.learn.graphql.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {
    public Optional<Book> findByTitle(String title);
}
