package com.adminService.admin.services;

import com.adminService.admin.dtos.AdminDto;
import com.adminService.admin.dtos.Credentials;
import com.adminService.admin.entites.Admin;

import java.util.List;

public interface AuthService {

    public Admin findByAdminName(String adminName);
    public List<AdminDto> findAll();
    public AdminDto findByUsername(String adminName);
    public AdminDto register(Admin data);

    public String generateToken(String adminName);
    public String validateToken(String token);
    public void saveToken(String adminName,String token);
    public String getToken(String adminName);
    public AdminDto login(Credentials data);
}
