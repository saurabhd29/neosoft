package com.logistics.transport.Repository;


import com.logistics.transport.Entites.TransportDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransportDetailsRepository extends JpaRepository<TransportDetails,Integer> {
    Optional<TransportDetails> findByEmail(String email);
    TransportDetails findByTransporterId(int transporterId);
}
