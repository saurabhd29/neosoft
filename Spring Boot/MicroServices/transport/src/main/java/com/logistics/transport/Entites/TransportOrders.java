package com.logistics.transport.Entites;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;



@Entity
public @Data class TransportOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transporterOrderId;

    private int transporterId;

    private int serviceId;

    private String pickUpLocation;

    private String deliveryLocation;

    private  String orderStatus;

    /*public int getTransporterOrderId() {
        return transporterOrderId;
    }

    public void setTransporterOrderId(int transporterOrderId) {
        this.transporterOrderId = transporterOrderId;
    }

    public int getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(int transporterId) {
        this.transporterId = transporterId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }*/
}
