package com.logistics.transport.ServiceImpl;


import com.logistics.transport.Dtos.TOrdersDTO;
import com.logistics.transport.Entites.TransportOrders;
import com.logistics.transport.Repository.TransportDetailsRepository;

import com.logistics.transport.Dtos.DtoEntityConverter;

import com.logistics.transport.Repository.TransportOrdersRepository;
import com.logistics.transport.Service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    private TransportDetailsRepository transportDetailsRepository;

    @Autowired
    private TransportOrdersRepository transportOrdersRepository;

    @Autowired
    private DtoEntityConverter converter;


    public String saveOrder(TransportOrders newOrder){
        TransportOrders t = transportOrdersRepository.save(newOrder);

        if(t!=null){
            return "Success";
        }
        return "Error";
    }



    public List<TOrdersDTO> getAllOrders() {

        List<TransportOrders> users = transportOrdersRepository.findAll();
        List<TOrdersDTO> usersResult = new ArrayList<>();

        Iterator<TransportOrders> itr = users.iterator();

        while (itr.hasNext()) {
            TOrdersDTO result = converter.toOrdersDto(itr.next());
            usersResult.add(result);
        }
        return usersResult;
    }


}
