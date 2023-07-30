package com.logistics.transport.Service;

import com.logistics.transport.Dtos.TUserDTO;
import com.logistics.transport.Entites.TransportDetails;

import java.util.List;
import java.util.Optional;

public interface TransportUserService {

    public TUserDTO save(TransportDetails reg);

    public TransportDetails findUserById(int transporterId);

    public Optional<TransportDetails> findByEmail(String email);

    public List<TUserDTO> getAllUsers();
}
