package com.corsojava.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corsojava.library.model.Borrowing;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Integer>{

}
