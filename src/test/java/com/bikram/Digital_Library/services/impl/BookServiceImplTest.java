package com.bikram.Digital_Library.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bikram.Digital_Library.entities.Author;
import com.bikram.Digital_Library.entities.Book;
import com.bikram.Digital_Library.entities.Genre;
import com.bikram.Digital_Library.exceptions.ResourceNotFoundException;
import com.bikram.Digital_Library.repositories.BookRepository;
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
	
	@Mock
	private BookRepository bookRepository;
	
	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	@Test
	void testGetBookById() {
		// mock object
		Book mockBook = new Book(1, "My Mock Book", "MyMockBook", 100, LocalDate.of(2025, 1, 1), 50, Genre.INSPIRATION, new Author(101, "Bikram Dey"));
		// precondition
		when(bookRepository.findById(1)).thenReturn(Optional.of(mockBook));
		// method call
		Book book = bookServiceImpl.getBookById(1);
		// assertEquals
		assertEquals(mockBook.getBookName(), book.getBookName());
		assertEquals( mockBook.getTitle(), book.getTitle());
		assertEquals(mockBook.getAuthor().getAuthorName(), book.getAuthor().getAuthorName());
		
		// verify
		verify(bookRepository, times(1)).findById(1);
		
	}
	
	@Test
	void testGetBookById_NotFound() {
		// pre-condition
		when(bookRepository.findById(2)).thenReturn(Optional.empty());
		// assertThrows
		Exception exception = assertThrows(ResourceNotFoundException.class, () -> bookServiceImpl.getBookById(2));
		// assertEquals
		assertEquals("Book Not Found !!", exception.getMessage());
		// verify
		verify(bookRepository, times(1)).findById(2);
	}

}
