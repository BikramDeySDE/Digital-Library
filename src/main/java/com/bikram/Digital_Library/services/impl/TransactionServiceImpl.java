package com.bikram.Digital_Library.services.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikram.Digital_Library.entities.Book;
import com.bikram.Digital_Library.entities.Transaction;
import com.bikram.Digital_Library.entities.TransactionType;
import com.bikram.Digital_Library.entities.User;
import com.bikram.Digital_Library.exceptions.OutOfStockException;
import com.bikram.Digital_Library.exceptions.ResourceNotFoundException;
import com.bikram.Digital_Library.repositories.BookRepository;
import com.bikram.Digital_Library.repositories.TransactionRepository;
import com.bikram.Digital_Library.repositories.UserRepository;
import com.bikram.Digital_Library.services.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	// borrow a book
	@Override
	public Book borrowBook(int userId, int bookId) {
		
		User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not Found with user-id : " + userId));
		Book book = bookRepository.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Book Not Found with book-id : " + bookId));
		
		int stock = book.getStock();
		if(stock>0) {
			Transaction transaction = new Transaction();
			transaction.setUser(user);
			transaction.setBook(book);
			transaction.setBorrowedDate(LocalDate.now());
			transaction.setTransactionType(TransactionType.BORROW);
			transaction.setAmount(book.getCost());
			
			book.setStock(stock-1);
			bookRepository.save(book);
			
			transactionRepository.save(transaction);
		}else {
			throw new OutOfStockException(book.getBookName());
		}
		
		return book;
	}

}
