package com.CockroachDb.controller;


import com.CockroachDb.Entity.ClientData;
import com.CockroachDb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class CockroachDb_AccountController {

    @Autowired
    private AccountService service;

    @GetMapping()
    public List<ClientData> getAlAccounts(){
        return service.getAccounts();
    }

    @GetMapping("/{id}")
    public Optional<ClientData> getAccountById(@PathVariable long id){
        return service.getAccountById(id);
    }

    @PostMapping()
    public ClientData create(@RequestBody ClientData account){
        return service.create(account);
    }

    @DeleteMapping()
    public String delete(@RequestBody ClientData account ){
        return service.delete(account);
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable long id , @RequestBody ClientData data){
        return service.update(id,data);
    }
}
