package com.bikram.Digital_Library.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikram.Digital_Library.dtos.AuthorDto;
import com.bikram.Digital_Library.entities.Author;
import com.bikram.Digital_Library.repositories.AuthorRepository;
import com.bikram.Digital_Library.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	private AuthorRepository authorRepository;
	
	// add new author
	@Override
	public Author addAuthor(AuthorDto authorDto) {
		Author author = new Author();
		BeanUtils.copyProperties(authorDto, author);
		Author addedAuthor = authorRepository.save(author);
		return addedAuthor;
	}

	// get all authors
	@Override
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}
	
}
