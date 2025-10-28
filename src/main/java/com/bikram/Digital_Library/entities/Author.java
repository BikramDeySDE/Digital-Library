package com.bikram.Digital_Library.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Author {
	@Id
	private int authorId;
	@NotBlank(message = "Author name must not be blank !!")
	private String authorName;
}
