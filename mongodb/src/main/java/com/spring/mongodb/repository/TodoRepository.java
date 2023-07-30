package com.spring.mongodb.repository;

import com.spring.mongodb.Dto.TodoDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends MongoRepository<TodoDto,String> {

   public Optional<TodoDto> findByTodo(String todo);

}
