package com.corsojava.library.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corsojava.library.model.User;
import com.corsojava.library.repository.UserRepository;

@Service
public class UserService {
	@Autowired 
	private UserRepository userRepository;
	
	public Optional<User> findById(int userId) {
		return userRepository.findById(userId);
	}

}
