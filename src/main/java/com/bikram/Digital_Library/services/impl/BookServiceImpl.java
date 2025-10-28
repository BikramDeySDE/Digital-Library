package com.bikram.Digital_Library.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bikram.Digital_Library.dtos.BookDto;
import com.bikram.Digital_Library.entities.Author;
import com.bikram.Digital_Library.entities.Book;
import com.bikram.Digital_Library.entities.Genre;
import com.bikram.Digital_Library.exceptions.ResourceAlreadyExistsException;
import com.bikram.Digital_Library.exceptions.ResourceNotFoundException;
import com.bikram.Digital_Library.repositories.AuthorRepository;
import com.bikram.Digital_Library.repositories.BookRepository;
import com.bikram.Digital_Library.services.BookService;
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	
	// add book
	@Override
	public Book addBook(BookDto bookDto) {
		
		Optional<Book> optionalBook = bookRepository.findById(bookDto.getBookId());
		if (optionalBook.isPresent()) {
			throw new ResourceAlreadyExistsException("Book Already present !!");
		}
		
		int authorId = bookDto.getAuthorId();
		Optional<Author> optionalAuthor = authorRepository.findById(authorId);
		
		if(optionalAuthor.isPresent()) {
			Book book = new Book();
			book.setAuthor(optionalAuthor.get());
			BeanUtils.copyProperties(bookDto, book);
			Book addedBook = bookRepository.save(book);
			return addedBook;
		}
		return null;
	}

	// find all books
	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	// find book by bookId
	@Override
	public Book getBookById(int bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book Not Found !!"));
		return book;
	}

	// find all books by genre
	@Override
	public List<Book> getBooksByGenre(Genre genre) {
		return bookRepository.findByGenre(genre);
	}
	
	// find books by author id
	@Override
	public List<Book> getBooksByAuthorId(int authorId) {
		return bookRepository.findByAuthorId(authorId);
	}

	// find all books by book name
	@Override
	public List<Book> getBooksByBookName(String bookName) {
		return bookRepository.findByBookName(bookName);
	}

	@Override
	public Page<Book> getAllBooksWithPaginationFeature(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return bookRepository.findAll(pageable);
	}

}
