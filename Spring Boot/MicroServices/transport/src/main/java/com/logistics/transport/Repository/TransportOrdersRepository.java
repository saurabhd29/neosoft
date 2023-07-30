package com.logistics.transport.Repository;



import com.logistics.transport.Entites.TransportOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportOrdersRepository extends JpaRepository<TransportOrders,Integer> {
}
