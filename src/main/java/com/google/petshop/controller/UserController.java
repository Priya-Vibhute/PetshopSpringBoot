package com.google.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.petshop.dto.UserDto;
import com.google.petshop.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;

	//	=====================================================================
    //	1)To fetch all users (GET-/users)
    //	======================================================================
	
	@GetMapping
	public ResponseEntity<List<UserDto>> fetchAllUsers()
	{
		return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.FOUND);
	}
	
    //	=====================================================================
    //	2)To fetch user by Id (GET-/users/123445-36473647-634763)
    //	=====================================================================
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> fetchUserById(@PathVariable String id)
	{
		return new ResponseEntity<UserDto>(userService.getUserById(id),
				                                          HttpStatus.OK);
	}
	
	 //	=====================================================================
    //	3)To  add user (POST-/users)
    //	=====================================================================
	
	@PostMapping
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto)
	{
		return new ResponseEntity<UserDto>(userService.createUser(userDto), 
				                                            HttpStatus.CREATED);
	}
	
	 //	=====================================================================
    //	4)To  update user (PUT-/users/1234-4578-545)
    //	=====================================================================
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable String id,
			                                                  @RequestBody UserDto userDto)
	{
	
		return new ResponseEntity<UserDto>(userService.updateUser(id, userDto), HttpStatus.OK);
	}
	
	 //	=====================================================================
    //	5)To  delete user (DELETE-/users/1234-4578-545)
    //	=====================================================================
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id)
	{
		userService.deleteUser(id);
		return new ResponseEntity<String>(id+ "deleted ", HttpStatus.OK);
	}
	
	
	 //	=====================================================================
    //	6)To  get user by email (GET-/users/find-by-email?email=abc@gmail.com)
    //	=====================================================================
	@GetMapping("/find-by-email")
	public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email)
	{
		
		return new ResponseEntity<UserDto>(userService.getByEmail(email), HttpStatus.FOUND);
	}
	
	
	
	
	
	
	
	
	
	
	
   

}
