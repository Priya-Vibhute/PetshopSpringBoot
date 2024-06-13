package com.google.petshop.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.petshop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
