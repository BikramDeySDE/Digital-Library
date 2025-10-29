package com.bikram.Digital_Library.services;

import com.bikram.Digital_Library.dtos.UserDto;
import com.bikram.Digital_Library.entities.User;

public interface UserService {
	
	User addUser(UserDto userDto);
	
}
