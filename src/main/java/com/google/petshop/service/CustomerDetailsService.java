package com.google.petshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.google.petshop.ExceptionHandlers.IdNotFoundException;
import com.google.petshop.entity.User;
import com.google.petshop.repositoy.UserRepository;

@Component
public class CustomerDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
		User user = userRepository.findByEmail(email).orElseThrow(()->
		new IdNotFoundException(email+" not found"));

		return user;
	}

}
