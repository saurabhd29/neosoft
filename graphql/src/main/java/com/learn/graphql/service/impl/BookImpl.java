package com.learn.graphql.service.impl;

import com.learn.graphql.entity.Book;
import com.learn.graphql.exceptions.ResourceNotFoundException;
import com.learn.graphql.repository.BookRepository;
import com.learn.graphql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Book create(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public List<Book> getAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book getBook(int BookId) {
        return this.bookRepository.findById(BookId).orElseThrow(()-> new ResourceNotFoundException("Book Not found"));
    }
}
