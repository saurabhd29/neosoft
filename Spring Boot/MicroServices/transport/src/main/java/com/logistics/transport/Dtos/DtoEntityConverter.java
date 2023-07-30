package com.logistics.transport.Dtos;

import com.logistics.transport.Entites.TransportDetails;
import com.logistics.transport.Entites.TransportOrders;
import org.springframework.stereotype.Component;

@Component
public class DtoEntityConverter {

    public TUserDTO toUserDto(TransportDetails user)
    {

        TUserDTO dto= new TUserDTO();

        dto.setTransporterId(user.getTransporterId());
        dto.setName(user.getName());
        dto.setCity(user.getCity());
        dto.setEmail(user.getEmail());
//        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());

        dto.setRole(user.getRole());
        return dto;
    }

    public TOrdersDTO toOrdersDto(TransportOrders user)
    {

        TOrdersDTO dto= new TOrdersDTO();

        dto.setTransporterId(user.getTransporterId());
        dto.setTransporterOrderId(user.getTransporterOrderId());
        dto.setServiceId(user.getServiceId());
        dto.setPickUpLocation(user.getPickUpLocation());
        dto.setDeliveryLocation(user.getDeliveryLocation());
        dto.setOrderStatus(user.getOrderStatus());


        return dto;
    }
}
