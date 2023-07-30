package com.CockroachDb.service;


import com.CockroachDb.Entity.ClientData;
import com.CockroachDb.exception.ResourceNotFoundException;
import com.CockroachDb.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository repository;
    @Override
    public List<ClientData> getAccounts() {
        return repository.findAll();
    }

    @Override
    public Optional<ClientData> getAccountById(long id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Data not found for given id:" + id)));
    }

    @Override
    public ClientData create(ClientData account) {
        return repository.save(account);
    }

    @Override
    public String delete(ClientData account) {
        repository.delete(account);
        return "Successfully removed the record ";
    }
    @Override
    public String update(long id , ClientData updatedData){
      repository.update(updatedData.getUsername(),updatedData.getSurname(),updatedData.getFirstname(),id);
      return "Updated successfully";
    }
}
