package com.spring.mongodb.controller;

import com.spring.mongodb.Dto.TodoDto;
import com.spring.mongodb.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("/todo")
@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/get-all-todos")
    public ResponseEntity<?> getAllTodos(){
        List<TodoDto> todoDtoList = todoRepository.findAll();
        if(todoDtoList!=null){
            return ResponseEntity.ok(todoDtoList);
        }
        return ResponseEntity.ok("Not Available ");
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getTodosById(@PathVariable("id") String id){
        Optional<TodoDto> todoDtoOptional = todoRepository.findById(id);

        if(todoDtoOptional.isPresent()){
            return ResponseEntity.ok(todoDtoOptional.get());
        }
        return ResponseEntity.ok("Not Found ");
    }

    @DeleteMapping("/delete-todos/{id}")
    public ResponseEntity<?> deleteTodosBiId(@PathVariable("id") String id){
        Optional<TodoDto> todoDtoOptional = todoRepository.findById(id);

        if(todoDtoOptional.isPresent()){
            todoRepository.deleteById(id);
            return ResponseEntity.ok("Deleted id "+ id);
        }
        return ResponseEntity.ok("Not Found ");
    }

    @PutMapping("/todos-by-id/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody TodoDto todoDto){
        Optional<TodoDto> todoDtoOptional = todoRepository.findById(id);

        if(todoDtoOptional.isPresent()){
            TodoDto todoDtoToSave = todoDtoOptional.get();
            todoDtoToSave.setTodo(todoDto.getTodo());
            todoDtoToSave.setDescription(todoDto.getDescription());
            todoDtoToSave.setUpdatedAt(new Date((System.currentTimeMillis())));
            todoRepository.save(todoDtoToSave);
            return ResponseEntity.ok(todoDtoOptional.get());
        }
        return ResponseEntity.ok("Not Found ");
    }

    @PostMapping("/save-todos")
    public ResponseEntity<?> saveTodos(@RequestBody TodoDto todoDto){

        todoDto.setCreatedAt(new Date(System.currentTimeMillis()));
        TodoDto t = todoRepository.save(todoDto);

        if(t!=null){
            return ResponseEntity.ok(t);
        }
        return ResponseEntity.ok("Not saved ");
    }
}
