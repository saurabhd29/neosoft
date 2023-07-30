package com.redis.caching.repository;

import com.redis.caching.entity.Product1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    Logger logger = LoggerFactory.getLogger(ProductDao.class);
    public static final String HASH_KEY = "Product1";
    @Autowired
    @Qualifier("Quick1")
    private RedisTemplate template1;

    public Product1 save(Product1 product){
        logger.info("save");
        template1.opsForHash().put(HASH_KEY,product.getId(),product);
        return product;
    }

    public List<Product1> findAll(){
        logger.info("inside findAll");
        return template1.opsForHash().values(HASH_KEY);
    }

    public Product1 findProductById(int id){
        logger.info("inside id from DB");
        return (Product1) template1.opsForHash().get(HASH_KEY,id);
    }


    public String deleteProduct(int id){
         template1.opsForHash().delete(HASH_KEY,id);
        return "product removed !!";
    }
}
