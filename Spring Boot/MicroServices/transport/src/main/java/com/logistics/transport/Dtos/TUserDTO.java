package com.logistics.transport.Dtos;

public class TUserDTO {

    public TUserDTO(){}
    private int transporterId;


    private String name;

    private String city;

    private  String role;

    private  String email;

    private  String password;

    public int getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(int transporterId) {
        this.transporterId = transporterId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

/*    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/
}
