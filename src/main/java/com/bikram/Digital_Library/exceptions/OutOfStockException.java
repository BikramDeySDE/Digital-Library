package com.bikram.Digital_Library.exceptions;

public class OutOfStockException extends RuntimeException{

	public OutOfStockException(String bookName) {
		super(bookName + " is currently Out of Stock !!");
	}
	
}
