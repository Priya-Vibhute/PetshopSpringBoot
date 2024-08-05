package com.google.petshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.petshop.dto.UserDto;
import com.google.petshop.entity.Product;
import com.google.petshop.entity.User;
import com.google.petshop.repositoy.ProductRepository;
import com.google.petshop.repositoy.UserRepository;
import com.google.petshop.service.UserService;

@SpringBootTest
class PetshopApplicationTests {
	
	@Autowired
	UserService service;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository ProdRepo;

	@Test
	void contextLoads() {
	}
	
	
	@Test
	public void testAdd() {
		
		Calculator c1 = new Calculator();
		
		int actualResult = c1.add(10, 10);
		
		int excpectedRestult=20;
		
		assertEquals(excpectedRestult, actualResult);
	}
	
	@Test
	public void testIsEven() {
		Calculator c1 = new Calculator();
		
		assertTrue(c1.isEven(6));
	}
	
	@Test
	public void testGetAllUsers() {
		List<UserDto> users = service.getAllUsers();
		
		assertTrue(users.size()>0);
	}
	
	@Test
	public void testProdRepo() {
	List<Product> prod = ProdRepo.findAll();
		
		assertTrue(prod.size()>0);
	}
	
	@Test
	public void testCreateUser() {
		UserDto userDto = new UserDto();
		
		userDto.setId("test123");
		userDto.setName("testuser");
		userDto.setAge(25);
		userDto.setPassword("Test1233");
		userDto.setConfirmPassword("Test1233");
		userDto.setEmail("test2@gmail.com");
		
		UserDto createduser = service.createUser(userDto);
		
		
		assertTrue(userRepository.existsById(createduser.getId()));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
