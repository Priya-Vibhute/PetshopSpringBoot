package com.google.petshop.repositoy;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.petshop.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
      // Custom Finder methods	
	
	  // To search data with the help of email
       Optional<User> findByEmail(String email);
       
      // To search data with the help of name  
       List<User> findByName(String name);
       
      //To search users which contains given keyword (LIKE)
       List<User>  findByNameContaining(String keyword);
       

}
