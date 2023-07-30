package com.adminService.admin.dtos;

import com.adminService.admin.entites.Admin;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DtoConverter {

    public AdminDto toAdminDto(Optional<Admin> admin){
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminName(admin.get().getAdminName());
        adminDto.setId(admin.get().getId());
        adminDto.setToken(admin.get().getToken());
        return adminDto;
    }

    public AdminDto toAdminDto1(Admin admin){
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminName(admin.getAdminName());
        adminDto.setId(admin.getId());
        adminDto.setToken(admin.getToken());
        return adminDto;
    }
}
