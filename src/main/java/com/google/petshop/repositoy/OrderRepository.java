package com.google.petshop.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.petshop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String>{

}
