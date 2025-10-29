package com.bikram.Digital_Library.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
	private int userId;
	@NotBlank(message = "Please enter First Name !!")
	private String firstName;
	@NotBlank(message = "Please enter Last Name !!")
	private String lastName;
	@Column(unique = true)
	@Email(message = "Please enter a valid Email !!")
	private String emailId;
}
