package com.corsojava.library.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.library.model.Book;
import com.corsojava.library.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService bookService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> createBook(@Valid @RequestBody Book book) {
		try {			
			return new ResponseEntity<Object>(bookService.create(book), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<Object> getAllBooks(@RequestParam(required = false) String title) {
		try {
			List<Book> books = new ArrayList<Book>();

			if (title == null)
				bookService.findAllBooks().forEach(books::add);
			else
				bookService.findAllBooksByWord(title).forEach(books::add);

			if (books.isEmpty()) {
				return new ResponseEntity<Object>("No books found.",HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<Object>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
