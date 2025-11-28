package com.bikram.Digital_Library.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {
	@Id
	private int authorId;
	@NotBlank(message = "Author name must not be blank !!")
	private String authorName;
}
