package com.learn.graphql.service.impl;


import com.learn.graphql.dtos.Request.InventoryRequestBean;
import com.learn.graphql.entity.Book;
import com.learn.graphql.exceptions.ResourceNotFoundException;
import com.learn.graphql.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class InventoryServiceImpl {

    @Autowired
    private BookRepository bookRepository;

    public Boolean inventoryCheck(InventoryRequestBean inventoryBean){
        Optional<Book> book = Optional.ofNullable(this.bookRepository.findById(Integer.valueOf(inventoryBean.getProductId())).orElseThrow(() -> new ResourceNotFoundException("HardDriveName with given name is not found on server!!:" + inventoryBean.getProductId())));
        log.info(String.valueOf(book.isPresent()));
        log.info(String.valueOf(book.get().getQty()>inventoryBean.getQty()));
        if(book.isPresent() && book.get().getQty()>=inventoryBean.getQty()){
            book.get().setQty(book.get().getQty()-inventoryBean.getQty());
            this.bookRepository.saveAndFlush(book.get());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
