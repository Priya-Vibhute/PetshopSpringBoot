package com.google.petshop.repositoy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.google.petshop.entity.Product;

@CrossOrigin
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
   List<Product> findByNameContains(String name);
   List<Product> findByPriceLessThanEqual(int price);
   List<Product> findByPriceGreaterThanEqual(int price);
   List<Product> findByPriceBetween(int startPrice,int endPrice);
   
   

}
