package com.google.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.petshop.entity.User;
import com.google.petshop.login.LoginRequest;
import com.google.petshop.login.LoginResponse;
import com.google.petshop.repositoy.UserRepository;
import com.google.petshop.security.JwtHelper;
import com.google.petshop.service.UserService;

import lombok.experimental.Helper;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtHelper jwtHelper;
	@Autowired
	UserRepository repository;
	@Autowired
	UserService service;
	
	@PostMapping
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
		
		
		
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		
		User user = repository.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new RuntimeException("email not found"));
		String token = jwtHelper.generateToken(user);
		
		return new ResponseEntity<LoginResponse>(new LoginResponse(token, service.entityToDto(user)), HttpStatus.OK);
		
	}
}
