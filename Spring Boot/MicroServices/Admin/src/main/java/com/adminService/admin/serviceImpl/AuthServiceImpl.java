package com.adminService.admin.serviceImpl;

import com.adminService.admin.dtos.AdminDto;
import com.adminService.admin.dtos.Credentials;
import com.adminService.admin.dtos.DtoConverter;
import com.adminService.admin.entites.Admin;
import com.adminService.admin.exceptions.ResourceNotFoundException;
import com.adminService.admin.repository.AuthRepository;
import com.adminService.admin.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {

    public final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    private DtoConverter converter;
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JwtService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<AdminDto> findAll(){
         List<Admin> adminList =authRepository.findAll();
         List<AdminDto> adminDtoList = new ArrayList<>();
         logger.info("Inside FindAll");
         Iterator itr = adminList.iterator();
         while(itr.hasNext()){
             AdminDto adminDto = converter.toAdminDto1((Admin) itr.next());
             adminDtoList.add(adminDto);
         }
        return adminDtoList;
    }
    public Admin findByAdminName(String adminName){
        Optional<Admin> admin = authRepository.findByAdminName(adminName);
        if(admin.isPresent()){
            /*AdminDto adminDto = converter.toAdminDto(admin.get());
            return adminDto;*/
            return admin.get();
        }
        return null;
    }
    public AdminDto findByUsername(String adminName){
        Optional<Admin> admin = Optional.ofNullable(authRepository.findByAdminName(adminName).orElseThrow(() -> new ResourceNotFoundException("adminName with given name is not found on server!!: "+ adminName)));
        if(admin.isPresent()){
            AdminDto adminDto = converter.toAdminDto1(admin.get());
            return adminDto;
        }
        return null;
    }

    public String validateToken(String token){
        try {
            String rs = service.validateToken(token);
            if(rs.equals("Valid"))
                return "Valid Token";
            else
                return "Invalid";
        }
        catch (Exception e){
            return "Not valid";
        }
    }
    public String generateToken(String adminName){
        Optional<Admin> admin = authRepository.findByAdminName(adminName);
        if(admin.isPresent()){
            return service.generateToken(adminName);
        }
        return null;
    }

    public void saveToken(String adminName,String token){
        Optional<Admin> admin = authRepository.findByAdminName(adminName);
        Admin ad = admin.get();
        ad.setToken(token);
        authRepository.save(ad);
    }

    public String getToken(String adminName){
        Optional<Admin> admin = authRepository.findByAdminName(adminName);
        if(admin.isPresent()){
            return admin.get().getToken();
        }
        return null;
    }

    public AdminDto register(Admin data){
        Optional<Admin> admin = authRepository.findByAdminName(data.getAdminName());
        logger.info("Inside Register");
        if(admin.isEmpty()){

            data.setPassword(passwordEncoder.encode(data.getPassword()));
            Admin adminNew = authRepository.save(data);
            AdminDto adminDto = converter.toAdminDto1(adminNew);

            return adminDto;
        }

        return null;
    }

    public AdminDto login(Credentials data){

        Optional<Admin> admin = authRepository.findByAdminName(data.getAdminName());
        if(admin.isPresent() && passwordEncoder.matches(data.getPassword(),admin.get().getPassword())){

            AdminDto adminDto = converter.toAdminDto(admin);
            String token = service.generateToken(admin.get().getAdminName());
            Admin ad = admin.get();
            ad.setToken(token);
            authRepository.save(ad);
            adminDto.setToken(token);

            return adminDto;
        }
        return null;
    }
}
