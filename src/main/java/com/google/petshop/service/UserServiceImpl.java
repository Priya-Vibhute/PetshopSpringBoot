package com.google.petshop.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.petshop.dto.UserDto;
import com.google.petshop.entity.User;
import com.google.petshop.repositoy.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		userDto.setId( UUID.randomUUID().toString());
			
		User savedUser = userRepository.save(dtoToEntity(userDto));	
			
		return entityToDto(savedUser);
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		
		User user = userRepository.findById(id).orElseThrow(()->
		                            new RuntimeException(id+" not found"));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAge(userDto.getAge());
		user.setAddress(userDto.getAddress());
		
		User savedUser = userRepository.save(user);	
		
		return entityToDto(savedUser);
	}

	@Override
	public void deleteUser(String id) {
		
		User user = userRepository.findById(id).orElseThrow(()->
		                   new RuntimeException(id+" not found"));
		userRepository.delete(user);
				
	}

	@Override
	public UserDto getUserById(String id) {
		
		 User user = userRepository.findById(id).orElseThrow(()->
		                      new RuntimeException(id+" not found"));	
		return entityToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		
		List<UserDto> userDtoList = users.stream()
		.map(user->entityToDto(user))
		.collect(Collectors.toList());

		return userDtoList;
	}

	@Override
	public UserDto entityToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setAge(user.getAge());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());	
		userDto.setAddress(user.getAddress());
		return userDto;
	}

	@Override
	public User dtoToEntity(UserDto userDto) {
		User user=new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setAge(userDto.getAge());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());	
		user.setAddress(userDto.getAddress());
		return user;
	}

	@Override
	public UserDto getByEmail(String email) {
		
		User user = userRepository.findByEmail(email)
		        .orElseThrow(()->new RuntimeException(email+"Not found"));
		
		return entityToDto(user);
	}

	@Override
	public List<UserDto> getByName(String name) {
		
		return userRepository.findByName(name)
				 .stream()
				 .map(user->entityToDto(user))
				 .collect(Collectors.toList());
	}

	@Override
	public List<UserDto> getByNameContaining(String keyword) {
		
		return userRepository.findByNameContaining(keyword)
				.stream()
				.map(user->entityToDto(user))
				.collect(Collectors.toList());
	}

}
