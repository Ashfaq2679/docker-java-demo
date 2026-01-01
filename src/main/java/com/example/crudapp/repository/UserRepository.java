package com.example.crudapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crudapp.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
