package com.movieReviewApp.userms.controller;

import java.util.List;

//import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieReviewApp.userms.dto.LoginUserRequest;
import com.movieReviewApp.userms.dto.ReviewDetails;
import com.movieReviewApp.userms.entity.User;
import com.movieReviewApp.userms.service.UserService;

@RestController
@RequestMapping("/wi-reviewz/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@GetMapping
	public List<User> getAllRegisteredUserDetails(){
		return service.getAll();
	}
	
	//To get User Details
	@GetMapping("/{userName}")
	public User getUserDetails(@PathVariable String userName) {
		return service.getUserDetails(userName);
	}
	
	
	//To register a user
	@PostMapping("/register")
	public User register(@RequestBody User user){
		return service.register(user);
	}
	
	//Login
	@GetMapping("/login")
	public User getUserReviews(@RequestBody LoginUserRequest login) {
		return service.login(login);
	}
	
	//To get All Reviews by user
	@GetMapping("/{username}/reviews")
	public List<ReviewDetails> getAllReviews(@PathVariable String username) {
		return service.getAllReviews(username);
	}
	
	
	//Delete user
	@DeleteMapping("/delete/{userName}")
	public String deleteUser(@PathVariable String userName){
			return service.removeUser(userName);
	}
	
	//Update a user details
	@PutMapping("/update")
	public User updateUser(@RequestBody User user){
			return service.updateUser(user);
	}
	
	//To update user status
	@PutMapping("/{userName}/updateStatus")
	public User updateUserStatus(@PathVariable String userName) {
		return service.updateUserActiveStatus(userName);
	}
	
	//To update User Role
	@PutMapping("/{userName}/updateRole")
	public User updateUserRole(@PathVariable String userName) {
		return service.updateUserRole(userName);
	}
	
}
