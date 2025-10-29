package com.bikram.Digital_Library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bikram.Digital_Library.entities.Book;
import com.bikram.Digital_Library.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/borrow")
	public ResponseEntity<Book> borrowBook(@RequestParam("userId") int userId, @RequestParam("bookId") int bookId){
		Book borrowedBook = transactionService.borrowBook(userId, bookId);
		return new ResponseEntity<Book>(borrowedBook, HttpStatus.CREATED);
	}

}
