package com.corsojava.library.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.library.exceptions.NotEnoughCopiesException;
import com.corsojava.library.model.Book;
import com.corsojava.library.model.Borrowing;
import com.corsojava.library.model.User;
import com.corsojava.library.service.BookService;
import com.corsojava.library.service.BorrowingService;
import com.corsojava.library.service.UserService;

@RestController
@RequestMapping("/borrowing")
public class BorrowingController {
	@Autowired
	BorrowingService borrowingService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	//nuovo prestito
	//ritorna ResponseEntity<Object> perché in alcuni casi nel body ritorno un borrowing, in altri casi una stringa (errore)
	@PostMapping("/create")
	public ResponseEntity<Object> create(@RequestBody BorrowingCreateParam borrowingParam) {
		try {			
			Optional<Book> book=bookService.findById(borrowingParam.getBookId());
			Optional<User> user=userService.findById(borrowingParam.getUserId());
			if (!book.isPresent())
				return new ResponseEntity<Object>(
					"Unable to find book: "+borrowingParam.getBookId(), HttpStatus.FORBIDDEN);
			else
				if (!user.isPresent())
					return new ResponseEntity<Object>(
						"Unable to find user: "+borrowingParam.getUserId(), HttpStatus.FORBIDDEN);
				else {
					Borrowing borrowing=new Borrowing();
					borrowing.setBook(book.get());
					borrowing.setUser(user.get());
					return new ResponseEntity<Object>(
						borrowingService.create(borrowing), HttpStatus.CREATED);
				}
					
		}
		catch (NotEnoughCopiesException e) {
			return new ResponseEntity<Object>(
				e.getMessage(), HttpStatus.FORBIDDEN);
			}
		catch (Exception e) {
			return new ResponseEntity<Object>(
				e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//chiusura di un prestito
	@PutMapping("/close")
	public ResponseEntity<Object> close(@RequestBody BorrowingCloseParam borrowingCloseParam) {
		try {			
			Optional<Borrowing> borrowing=borrowingService.findById(borrowingCloseParam.getBorrowingId());
			if (!borrowing.isPresent())
				return new ResponseEntity<Object>(
					"Unable to find borrowing: "+borrowingCloseParam.getBorrowingId(), HttpStatus.FORBIDDEN);
			else
				return new ResponseEntity<Object>(
					borrowingService.close(borrowing.get()), HttpStatus.OK);
		}
					
		 catch (Exception e) {
			return new ResponseEntity<Object>(
				e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/list")
	public ResponseEntity<List<Borrowing>> listBorrowings() {
		try {
			return new ResponseEntity<List<Borrowing>>(
				borrowingService.list(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
				null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
