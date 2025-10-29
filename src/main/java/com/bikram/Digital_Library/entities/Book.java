package com.bikram.Digital_Library.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class Book {
	@Id
	private int bookId;
	@NotBlank(message = "Please enter a Book Name !!")
	private String bookName;
	private String title;
	@Positive(message = "cost must be greater than zero !!")
	private float cost;
	@PastOrPresent(message = "Publish Date must be of past or present !!")
	@JsonFormat(pattern = "dd-MM-yyyy") // ex : 10-01-2025
	private LocalDate publishDate;
	@Min(value = 0, message = "stock can not be negative !!")
	private int stock;
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private Genre genre;
	@ManyToOne
	@JoinColumn(name = "author_id") // by-default, the name of the column will be fieldName_PKoftheEntity and where ever it sees a camel case it would convert into '_', by default the column name would be : author_author_id 
	private Author author; 
}
