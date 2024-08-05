package com.google.petshop.security;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	UserDetailsService detailsService;
	
	@Autowired
	AuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	JWTRequestFilter jwtRequestFilter;
	
//	@Bean
//	public UserDetailsService userDetailsService()
//	{
//		UserDetails user = User.withUsername("Sheshmani")
//		.password(passwordEncoder().encode("User123"))
//		.roles("user")
//		.build();
//		
//		UserDetails admin = User.withUsername("admin")
//		.password(passwordEncoder().encode("admin123"))
//		.roles("admin")
//		.build();
//		
//		return new InMemoryUserDetailsManager(user,admin);
//	}
	
	
	

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
    }
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.detailsService);
		daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		
		httpSecurity.cors(cors->cors.disable())
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(request->
		
		request.requestMatchers(HttpMethod.POST, "/users","/login")
		.permitAll()
		
		.requestMatchers(HttpMethod.GET,"/products/**").permitAll()
		.requestMatchers(HttpMethod.POST,"/product","/roles").hasAnyRole("admin")
		.requestMatchers(HttpMethod.PUT,"/products/**").hasAnyRole("admin","")
		.requestMatchers(HttpMethod.GET,"/users","/roles").hasAnyRole("admin")
		
		
		
		.anyRequest().authenticated()).httpBasic(Customizer.withDefaults()).exceptionHandling(config ->config.authenticationEntryPoint(authenticationEntryPoint))
		.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
		
	}
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	
	
	
	
}
