package com.bikram.Digital_Library.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.bikram.Digital_Library.dtos.BookDto;
import com.bikram.Digital_Library.entities.Book;
import com.bikram.Digital_Library.entities.Genre;

public interface BookService {
	
	Book addBook(BookDto bookDto);

	List<Book> getAllBooks();

	Book getBookById(int bookId);

	List<Book> getBooksByGenre(Genre genre);

	List<Book> getBooksByAuthorId(int authorId);

	List<Book> getBooksByBookName(String bookName);

	Page<Book> getAllBooksWithPaginationFeature(int pageNo, int pageSize);

	Page<Book> getAllBooksWithPaginationAndSortingFeature(int pageNo, int pageSize, String sort);

}
