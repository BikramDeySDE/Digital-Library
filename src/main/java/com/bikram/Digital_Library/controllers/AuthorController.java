package com.bikram.Digital_Library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikram.Digital_Library.dtos.AuthorDto;
import com.bikram.Digital_Library.entities.Author;
import com.bikram.Digital_Library.services.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;

	@PostMapping("/add")
	public ResponseEntity<Author> addAuthor(@RequestBody @Valid AuthorDto authorDto){
		Author addedAuthor = authorService.addAuthor(authorDto);
		return new ResponseEntity<Author>(addedAuthor, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Author>> getAllAuthor(){
		List<Author> authorList = authorService.getAllAuthors();
		return new ResponseEntity<List<Author>>(authorList, HttpStatus.OK);
	}
}
