package com.bikram.Digital_Library.services.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikram.Digital_Library.dtos.BorrowDto;
import com.bikram.Digital_Library.entities.Book;
import com.bikram.Digital_Library.entities.Penalty;
import com.bikram.Digital_Library.entities.Transaction;
import com.bikram.Digital_Library.entities.TransactionType;
import com.bikram.Digital_Library.entities.User;
import com.bikram.Digital_Library.exceptions.OutOfStockException;
import com.bikram.Digital_Library.exceptions.ResourceNotFoundException;
import com.bikram.Digital_Library.exceptions.StateMismatchedException;
import com.bikram.Digital_Library.payloads.AppConstants;
import com.bikram.Digital_Library.repositories.BookRepository;
import com.bikram.Digital_Library.repositories.PenaltyRepository;
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
	private PenaltyRepository penaltyRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	// borrow a book
	@Override
	public Book borrowBook(BorrowDto borrowDto) {
		
		int userId = borrowDto.getUserId();
		int bookId = borrowDto.getBookId();
		
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
			throw new OutOfStockException(book.getBookName() + "is currently out of stock !!");
		}
		
		return book;
	}
	
	// return a book
	@Override
	public Transaction returnBook(int transactionId) {
		// find the transaction
		Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(()-> new ResourceNotFoundException("Transaction not found with respective transaction id : " + transactionId));

		// transaction type is BORROW or not
		if (transaction.getTransactionType()!=TransactionType.BORROW) {
			throw new StateMismatchedException("Transaction status is currentlt not BORROW !!");
		}else {
			//we need to calculate the no of days
			LocalDateTime returnedDateTime = LocalDate.now().atStartOfDay();
			LocalDateTime borrowDateTime = transaction.getBorrowedDate().atStartOfDay();
			// for calculating the duration we should pass LocalDateTime object, atStartOfTheDay() gives LocalDateTime object from LocalDate
			Duration duration = Duration.between(borrowDateTime, returnedDateTime);
			
			// check if penalty is needed or not	
			int noOfDays = (int) duration.toDays();
			if(noOfDays > AppConstants.NO_OF_PENALTY_FREE_DAYS) {
				int noOfExtraDays = noOfDays - AppConstants.NO_OF_PENALTY_FREE_DAYS;
				Penalty penalty = new Penalty();
				penalty.setNoOfDaysDifference(noOfExtraDays);
				penalty.setAmount(noOfExtraDays * AppConstants.PENALTY_PER_DAY);
				penalty.setDescription("Late Fees");
				penaltyRepository.save(penalty);
				
				
				transaction.setPenalty(penalty);
				transaction.setAmount(transaction.getBook().getCost() + penalty.getAmount());
			}
			
			transaction.setReturnedDate(LocalDate.now());
			transaction.setTransactionType(TransactionType.RETURN);
			
			transactionRepository.save(transaction);
			
			Book book = transaction.getBook();
			book.setStock(book.getStock() + 1);
			bookRepository.save(book);

		}
		
		return transaction;
	}
	
	

}
