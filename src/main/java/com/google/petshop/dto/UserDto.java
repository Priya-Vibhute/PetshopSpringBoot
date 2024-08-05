package com.google.petshop.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.petshop.entity.Address;
import com.google.petshop.entity.Order;
import com.google.petshop.entity.Roles;
import com.google.petshop.validator.passwordMatch;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@passwordMatch
public class UserDto {
	
	private String id;
	@NotNull(message = "Username cannot be null")
	@NotBlank(message = "Username cannot be empty")
	@Size(min = 2,max = 52)
	private String name;

	
	@Pattern(regexp = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$")
	@NotNull
	private String email;
	@Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$")
	@NotNull
	private String password;
	private String confirmPassword;
	
	@Min(18)
	@Max(100)
	private int age;
	private Address address;
	private String image;
	@JsonIgnore
	private List<Order> orders;
	private Set<Roles> roles=new HashSet<Roles>();

}
