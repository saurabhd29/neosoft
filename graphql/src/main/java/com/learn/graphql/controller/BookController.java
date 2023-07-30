package com.learn.graphql.controller;

import com.learn.graphql.entity.Book;
import com.learn.graphql.service.BookService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
//@RequestMapping("/api/v1")
public class BookController {
    @Autowired
    private BookService bookService;

    @MutationMapping("createBook")
    public Book create(@Argument BookInput book) {
        log.info("book {}",book);
        Book b=new Book();

        b.setTitle(book.getTitle());
        b.setDescription(book.getDesc());
        b.setPrice(book.getPrice());
        b.setAuthor(book.getAuthor());
        b.setQty(book.getQty());
        b.setCreatedAt(Date.from(Instant.now()));
        return this.bookService.create(b);
    }
/*//    @PostMapping("/save")
    @MutationMapping("createBook")
    //public Book create(@RequestBody Book book){
    public Book create(@Argument BookInput book){
        Book b = new Book();
        b.setAuthor(book.getAuthor());
        b.setDesc(book.getDesc());
        b.setAuthor(book.getAuthor());
        b.setPrice(book.getPrice());
        b.setCreatedAt(Date.from(Instant.now()));
        log.info("book {}",book);
        return this.bookService.create(b);
    }*/


    @QueryMapping("allBooks")
    public List<Book> findAll(){
        return this.bookService.getAll();
    }


    @QueryMapping("getBook")
    public Book findById(@Argument("bookId") int bookId){
        return this.bookService.getBook(bookId);
    }


}

@Getter
@Setter
@ToString
class BookInput{
    private String title;
    private String desc;
    private int qty;
    private String author;
    private double price;
}
