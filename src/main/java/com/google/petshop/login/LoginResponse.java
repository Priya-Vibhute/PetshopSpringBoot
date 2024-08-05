package com.google.petshop.login;

import com.google.petshop.dto.UserDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	private String token;
	private UserDto dto;
}
