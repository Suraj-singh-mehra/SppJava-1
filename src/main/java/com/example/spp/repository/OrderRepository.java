package com.example.spp.repository;

import com.example.spp.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();

    List<Order> findByUserId( long userId);
}
