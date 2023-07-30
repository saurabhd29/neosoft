package com.logistics.transport.Service;

import com.logistics.transport.Dtos.TOrdersDTO;
import com.logistics.transport.Entites.TransportOrders;

import java.util.List;

public interface TransportService {
    public String saveOrder(TransportOrders newOrder);

    public List<TOrdersDTO> getAllOrders();


}
