package com.corsojava.library.exceptions;

import com.corsojava.library.model.Book;

public class NotEnoughCopiesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Book book;

	public NotEnoughCopiesException(Book book) {
		super("Not enough copies. Book: "+book.getTitle());
		this.book = book;
	}
	
	public Book getBook() {
		return book;
	}	

}