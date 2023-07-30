package com.redis.caching.controller;

import com.redis.caching.entity.Product1;
import com.redis.caching.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableCaching
@RequestMapping("/product1")
public class ProductController {

    @Autowired
    private ProductDao dao;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/add")
    @CacheEvict(value="Pro",  allEntries = true)
    @CachePut(cacheNames = "Product1", key="#product.id")
    public Product1 save(@RequestBody Product1 product) {
        return dao.save(product);
    }

    @GetMapping("/list")
    @Cacheable(value="Pro")
    public List<Product1> getAllProducts() {

        return dao.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key="#id", value="Product1")
    public Product1 findProduct(@PathVariable int id) {

        return dao.findProductById(id);
    }
    @DeleteMapping("/{id}")
    public String remove(@PathVariable int id)   {
        return dao.deleteProduct(id);
    }
}
