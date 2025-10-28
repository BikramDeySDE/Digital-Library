package com.bikram.Digital_Library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bikram.Digital_Library.dtos.BookDto;
import com.bikram.Digital_Library.entities.Book;
import com.bikram.Digital_Library.entities.Genre;
import com.bikram.Digital_Library.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	// add a book
	@PostMapping("/add")
	public ResponseEntity<Book> addBook(@RequestBody @Valid BookDto bookDto) {
		Book addeBook = bookService.addBook(bookDto);
		return new ResponseEntity<Book>(addeBook, HttpStatus.CREATED);
	}
	// get all books
	@GetMapping("/")
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> books = bookService.getAllBooks();
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	
	// get book by id
	@GetMapping("/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable("bookId") int bookId){
		Book book = bookService.getBookById(bookId);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	// get book by genre
	@GetMapping("/searchByGenre")
	public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam("genre") Genre genre){
		List<Book> bookList = bookService.getBooksByGenre(genre);
		return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	}
	
	// get book by author id
	@GetMapping("/author/{authorId}")
	public ResponseEntity<List<Book>> GetBooksByAuthorId(@PathVariable("authorId") int authorId) {
		List<Book> bookList = bookService.getBooksByAuthorId(authorId);
		return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
	}
	
	// get all books by book name
	@GetMapping("/name")
	public ResponseEntity<List<Book>> getAllBooksByBookName(@RequestParam("bookName") String bookName){
		List<Book> book = bookService.getBooksByBookName(bookName);
		return new ResponseEntity<List<Book>>(book, HttpStatus.OK);
	}
	
	@GetMapping("/pageNo/{pageNo}/pageSize/{pageSize}")
	public ResponseEntity<Page<Book>> getALlBooksWithPaginationFeature(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize){
		Page<Book> pageOfBooks = bookService.getAllBooksWithPaginationFeature(pageNo, pageSize);
		return new ResponseEntity<Page<Book>>(pageOfBooks, HttpStatus.OK);
	}
	
	
}
