package com.learn.graphql.service;

import com.learn.graphql.entity.Book;

import java.util.List;

public interface BookService {

    public Book create(Book book);

    List<Book> getAll();

    Book getBook(int BookId);


}
