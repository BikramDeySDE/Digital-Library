package com.bikram.Digital_Library.dtos;

import java.time.LocalDate;

import com.bikram.Digital_Library.entities.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;
@Data
public class BookDto {
	private int bookId;
	@NotBlank(message = "Please enter a Book Name !!")
	private String bookName;
	private String title;
	@Positive(message = "cost must be greater than zero !!")
	private float cost;
	@PastOrPresent(message = "Publish Date must be of past or present !!")
	@JsonFormat(pattern = "dd-MMM-yyyy") // ex : 10-jan-25
	private LocalDate publishDate;
	@Min(value = 0, message = "stock can not be negative !!")
	private int stock;
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private Genre genre;
	private int authorId;
}
