package com.user.ReplicateUser.repository;

import com.user.ReplicateUser.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<UserInfo,Integer> {
}
