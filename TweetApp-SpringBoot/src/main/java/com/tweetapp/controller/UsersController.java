package com.tweetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.request.UserRequest;
import com.tweetapp.response.UserResponse;
import com.tweetapp.service.UsersService;

@RestController
public class UsersController {

	@Autowired
	UsersService usersService;
	
	@RequestMapping(path = "/api/v1.0/tweets/users/all" , method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public UserResponse getAllUsers() {
		return usersService.getAllUsers();
	}
	
	@RequestMapping(path = "/register" , method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public UserResponse register(@RequestBody UserRequest request) {
		return usersService.register(request);
	}
	
	@RequestMapping(path = "/forgetPassword" , method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public UserResponse forgetPassword(@RequestBody UserRequest request) {
		return usersService.forgetPassword(request);
	}
	
	@RequestMapping(path = "/api/v1.0/user/search/{username}" , method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public UserResponse searchUsers(@PathVariable(name = "username") String username) {
		return usersService.searchUsers(username);
	}
	
	@RequestMapping(path = "/api/v1.0/user/getAllLoggedInUsers" , method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:3000")
	public UserResponse getAllLoggedInUsers() {
		return usersService.getAllLoggedInUsers();
	}
	
	
}
