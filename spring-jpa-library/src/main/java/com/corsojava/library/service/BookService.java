package com.corsojava.library.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corsojava.library.model.Author;
import com.corsojava.library.model.Book;
import com.corsojava.library.repository.AuthorRepository;
import com.corsojava.library.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}
	
	public List<Book> findAllBooksByWord(String word) {
		return bookRepository.findByTitleContainingIgnoreCase(word);
	}
	
	@Transactional
	public Book create(Book book) {
		for(Author author:book.getAuthors())
			authorRepository.save(author);
		return bookRepository.save(book);
	}
	
	public Optional<Book> findById(int bookId) {
		return bookRepository.findById(bookId);
	}

}
