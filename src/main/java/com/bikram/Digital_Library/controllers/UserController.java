package com.bikram.Digital_Library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikram.Digital_Library.dtos.UserDto;
import com.bikram.Digital_Library.entities.User;
import com.bikram.Digital_Library.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody @Valid UserDto userDto) {
		User addedUser = userService.addUser(userDto);		
		return new ResponseEntity<User>(addedUser, HttpStatus.CREATED);
	}
	
}
