package com.logistics.transport.Repository;

import com.logistics.transport.Entites.Customer;
import com.logistics.transport.Entites.TransportDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
