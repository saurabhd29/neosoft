package com.logistics.transport.ServiceImpl;

import com.logistics.transport.Dtos.DtoEntityConverter;
import com.logistics.transport.Dtos.TUserDTO;
import com.logistics.transport.Entites.TransportDetails;
import com.logistics.transport.Repository.TransportDetailsRepository;
import com.logistics.transport.Service.TransportUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TransportUserServiceImpl implements TransportUserService {

    @Autowired
    private TransportDetailsRepository transportDetailsRepository;

    @Autowired
    private DtoEntityConverter converter;

    public TUserDTO save(TransportDetails reg){
        Optional<TransportDetails> t1 = transportDetailsRepository.findByEmail(reg.getEmail());
        System.out.println("Save1"+t1);
        if(t1.isEmpty()) {
            System.out.println("2");
            TransportDetails t = transportDetailsRepository.save(reg);
            if (t != null) {
                t.setPassword("*********");
                TUserDTO t2 = converter.toUserDto(t);
                return t2;
            }

        }
        return null;
    }
    public TransportDetails findUserById(int transporterId){
        TransportDetails user = transportDetailsRepository.findByTransporterId(transporterId);
        return user;
    }

    public Optional<TransportDetails> findByEmail(String email){
        Optional<TransportDetails> user = transportDetailsRepository.findByEmail(email);
        return user;
    }
    public List<TUserDTO> getAllUsers() {

        List<TransportDetails> users = transportDetailsRepository.findAll();
        List<TUserDTO> usersResult = new ArrayList<>();

        Iterator<TransportDetails> itr = users.iterator();

        while (itr.hasNext()) {
            TUserDTO result = converter.toUserDto(itr.next());
            usersResult.add(result);
        }
        return usersResult;
    }

}
