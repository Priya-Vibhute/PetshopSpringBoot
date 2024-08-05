package com.google.petshop.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.petshop.entity.User;
import com.google.petshop.repositoy.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtHelper helper;
	
	@Autowired
	UserRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		 String authorization = request.getHeader("Authorization");
		 
		 String username =null;
		 String token =null;
		 
		 if (authorization != null && authorization.startsWith("Bearer")) {
			 
			 token = authorization.substring(7);
			 
			 username = helper.getUsernameFromToken(token);
		 };
		 
		 if(username != null) {
			 User user = repository.findByEmail(username).orElseThrow(()-> new RuntimeException("Email not found"));
//									or		 
// 			User user2 = repository.findByEmail(username).get();
			 
			 Boolean validateToken = helper.validateToken(token, user);
			 
			 if(validateToken) {
				 
			 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
		 usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
			 
		 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			 System.out.println("Authentication Sucessfull");
			 }
			 else {
				 System.out.println("Authentication Unsucessfull");
	}
			 	 
		 }
		 
		 filterChain.doFilter(request, response);
		 
		 
	}
}
