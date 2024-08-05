package com.google.petshop.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.petshop.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
