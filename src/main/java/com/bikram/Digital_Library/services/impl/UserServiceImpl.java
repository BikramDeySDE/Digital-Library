package com.bikram.Digital_Library.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikram.Digital_Library.dtos.UserDto;
import com.bikram.Digital_Library.entities.User;
import com.bikram.Digital_Library.repositories.UserRepository;
import com.bikram.Digital_Library.services.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	// add user
	@Override
	public User addUser(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return userRepository.save(user);
	}

}
