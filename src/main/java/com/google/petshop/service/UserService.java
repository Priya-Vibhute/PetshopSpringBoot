package com.google.petshop.service;

import java.util.List;

import com.google.petshop.dto.UserDto;
import com.google.petshop.entity.User;

public interface UserService {
	
	//Create User
   UserDto	createUser (UserDto userDto);
   
   //update User
   UserDto updateUser(String id,UserDto userDto);
   
   //delete user
   void deleteUser(String id);
   
   //get user by id  
   UserDto getUserById(String id);
   
   //fetch all users
   List<UserDto>  getAllUsers();
   
   //fetch user by email
    UserDto getByEmail(String email);
    
    //fetch user by name
    List<UserDto>  getByName(String name);
    
    //fetch user which contains given keyword in the name
    List<UserDto> getByNameContaining(String keyword);
    
    
   
   // entity to dto
   
    UserDto  entityToDto(User user);
   
   //dto to entity
    User dtoToEntity(UserDto userDto);
    
    
    
  
  
   
   
   

}
