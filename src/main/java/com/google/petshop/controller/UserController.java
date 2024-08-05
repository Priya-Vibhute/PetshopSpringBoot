package com.google.petshop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.petshop.dto.UserDto;
import com.google.petshop.entity.Order;
import com.google.petshop.repositoy.OrderRepository;
import com.google.petshop.service.FileService;
import com.google.petshop.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Value("${user.images}")
	String imagePath;
	@Autowired
	FileService fileService;
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
	
	@PostMapping("/{id}/create-order")
	public ResponseEntity<Order> createOrder(@RequestBody Order order,@PathVariable String id)
	{
		UserDto userDto = userService.getUserById(id);
		order.setUser(userService.dtoToEntity(userDto));
		Order savedOrder = orderRepository.save(order);
		
		return new ResponseEntity<Order>(savedOrder, HttpStatus.OK);
	}
	@PostMapping("/{id}/add-image")
	public ResponseEntity<String> addImage(@RequestParam("image") MultipartFile file,@PathVariable String id) {
		
		String uploadedImage =
				fileService.uploadImage(file, this.imagePath);
		UserDto userdto =userService.getUserById(id);
		userdto.setImage(uploadedImage);
		
		
		userService.updateUser(id, userdto);
		
		
		return new ResponseEntity<String>(uploadedImage+"added Successfully", HttpStatus.OK);
	}
	@GetMapping("/{id}/image")
	public void getImage(@PathVariable String id,HttpServletResponse httpServletResponse) {
		UserDto userdto = userService.getUserById(id);
		String imageName = userdto.getImage();
		
		
		InputStream image = fileService.getImage(imagePath, imageName);
		
		httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
		try {
			StreamUtils.copy(image, httpServletResponse.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
   

}
