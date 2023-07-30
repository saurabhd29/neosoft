package com.logistics.transport.Config;


import com.logistics.transport.Dtos.TUserDTO;
import com.logistics.transport.Entites.TransportDetails;
import com.logistics.transport.Repository.TransportDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TransportDetailsRepository repository;
    @Override
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {
       Optional<TransportDetails> user=repository.findByEmail(adminName);
        return user.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found with name:"+adminName));
    }
}
