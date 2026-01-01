package com.example.crudapp.service;

import com.example.crudapp.model.User;
import com.example.crudapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private final UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public User save(User user) {
		return repository.save(user);
	}

	public List<User> findAll() {
		return repository.findAll();
	}
}
