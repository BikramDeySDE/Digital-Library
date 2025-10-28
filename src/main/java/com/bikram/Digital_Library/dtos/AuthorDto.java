package com.bikram.Digital_Library.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorDto {
	private int authorId;
	@NotBlank(message = "Author name must not be blank !!")
	private String authorName;
}
