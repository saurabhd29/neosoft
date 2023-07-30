package com.adminService.admin.repository;

import com.adminService.admin.entites.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Admin,Integer> {

    Optional<Admin> findByAdminName(String adminName);


    Optional<Admin> findById(int integer);


}
