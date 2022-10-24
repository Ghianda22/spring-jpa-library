package com.corsojava.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corsojava.library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	//operazioni CRUD
	public List<Book> findByTitleContainingIgnoreCase(String word);
}
