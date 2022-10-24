package com.corsojava.library.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
public class Borrowing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "must be not null")
	@PastOrPresent(message = "must be in the past or in the present")
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	
	@ManyToOne
	@JsonManagedReference(value = "book")
	@JsonIgnore		//non includo in deserializzazione (quando chiamo l'API per creare il prestito invio solo idutente e idlibro nel JSON di richiesta)
	private Book book;
	
	
	@ManyToOne 
	@JsonManagedReference(value = "user")
	@JsonIgnore 	//non includo in deserializzazione //non includo in deserializzazione (quando chiamo l'API per creare il prestito invio solo idutente e idlibro nel JSON di richiesta)
	private User user;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
