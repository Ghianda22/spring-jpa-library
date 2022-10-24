package com.corsojava.library.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "title must be not null")
	private String title;
	
	@NotNull(message = "isbn must be not null")
	@Size(min = 13, max=13, message = "isbn length must be 13")
	private String isbn;
	
	@Positive(message = "price must be positive")
	private BigDecimal price;
	
	@NotNull(message="publisher must be not null")
	private String publisher;
	
	@NotNull(message = "genres must be not null")
	private String genres;
	
	@PositiveOrZero(message = "Available copies must be positive or zero")
	private int AvailableCopies;
	
	@ManyToMany	
	@JsonBackReference(value = "authorsList")
	private List<Author> authors;
	
	@OneToMany(mappedBy = "book")	//nome del corrispondente attributo su Borrowing
	@JsonBackReference(value = "borrowingList")		
	private List<Borrowing> borrowings;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public int getAvailableCopies() {
		return AvailableCopies;
	}

	public void setAvailableCopies(int availableCopies) {
		AvailableCopies = availableCopies;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Borrowing> getBorrowings() {
		return borrowings;
	}

	public void setBorrowings(List<Borrowing> borrowings) {
		this.borrowings = borrowings;
	}
	
	
	
}
