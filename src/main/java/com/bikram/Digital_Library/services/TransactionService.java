package com.bikram.Digital_Library.services;

import com.bikram.Digital_Library.dtos.BorrowDto;
import com.bikram.Digital_Library.entities.Book;
import com.bikram.Digital_Library.entities.Transaction;

public interface TransactionService {
	
	Book borrowBook(BorrowDto borrowDto);
	
	Transaction returnBook(int transactionId);
	
}
