package com.bikram.Digital_Library.services;

import java.util.List;

import com.bikram.Digital_Library.dtos.AuthorDto;
import com.bikram.Digital_Library.entities.Author;

public interface AuthorService {
	
	Author addAuthor(AuthorDto authorDto);

	List<Author> getAllAuthors();
}
