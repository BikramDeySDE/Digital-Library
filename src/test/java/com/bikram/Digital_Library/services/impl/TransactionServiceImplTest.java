package com.bikram.Digital_Library.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bikram.Digital_Library.entities.Author;
import com.bikram.Digital_Library.entities.Book;
import com.bikram.Digital_Library.entities.Genre;
import com.bikram.Digital_Library.entities.Penalty;
import com.bikram.Digital_Library.entities.Transaction;
import com.bikram.Digital_Library.entities.TransactionType;
import com.bikram.Digital_Library.repositories.BookRepository;
import com.bikram.Digital_Library.repositories.PenaltyRepository;
import com.bikram.Digital_Library.repositories.TransactionRepository;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

	@Mock
	private TransactionRepository transactionRepository;
	
	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private PenaltyRepository penaltyRepository;
	
	@InjectMocks
	private TransactionServiceImpl transactionServiceImpl;
	
	private Book mockBook;
	
	private Transaction mockTransaction;
	
	
	// initialize mock objects before each test cases
	@BeforeEach
	void init() {
		
		// mock objects
		
		mockBook = new Book(1, "My Mock Book", "MyMockBook", 100, LocalDate.of(2025, 1, 1), 50, Genre.INSPIRATION, new Author(101, "Bikram Dey"));

		mockTransaction = new Transaction();
		mockTransaction.setTransactionId(10001);
		mockTransaction.setTransactionType(TransactionType.BORROW);
		mockTransaction.setAmount(mockBook.getCost());
		mockTransaction.setBook(mockBook);
		
	}
	
	@Test
	void testReturnBook_WithOutPenalty() {
		
		mockTransaction.setBorrowedDate(LocalDate.of(2025, 11, 10));
		
		// pre-conditions
		when(transactionRepository.findById(10001)).thenReturn(Optional.of(mockTransaction));

		// method call
		transactionServiceImpl.returnBook(10001);
		
		// assertions
		assertEquals(TransactionType.RETURN, mockTransaction.getTransactionType()); // check if transaction type is changed to RETURN or not
		assertEquals(51, mockBook.getStock()); // check if available stock is increased by 1 or not
		assertEquals(LocalDate.now(), mockTransaction.getReturnedDate()); // check if return date correctly updated or not
		
		// verify
		verify(transactionRepository, times(1)).save(any(Transaction.class)); // check if any transaction is saved or not (here transaction should be updated)
		verify(bookRepository, times(1)).save(any(Book.class)); // check if any book is saved or not (here book should be updated)
		verify(penaltyRepository, times(0)).save(any(Penalty.class));
		
	}
	
	@Test
	void testReturnBook_WithPenalty() {
		
		mockTransaction.setBorrowedDate(LocalDate.of(2025, 10, 26));
		
		// pre-conditions
		when(transactionRepository.findById(10001)).thenReturn(Optional.of(mockTransaction));

		// method call
		transactionServiceImpl.returnBook(10001);
		
		// assertions
		assertEquals(150, mockTransaction.getPenalty().getAmount());
		assertEquals("Late Fees", mockTransaction.getPenalty().getDescription());
		
		assertEquals(51, mockBook.getStock());		
		assertEquals(TransactionType.RETURN, mockTransaction.getTransactionType());
		
		assertNotNull(mockTransaction.getPenalty());
		
		// verify
		verify(penaltyRepository, times(1)).save(any(Penalty.class));
		
		verify(bookRepository, times(1)).save(any(Book.class));
		verify(transactionRepository, times(1)).save(any(Transaction.class));
		
	}

}
