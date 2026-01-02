package com.example.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class DockerDemoApplication {
	@Autowired
	Environment env;

	public static void main(String[] args) {
		SpringApplication.run(DockerDemoApplication.class, args);
	}

    @Bean
    MongoClient mongoClient() {
		String mongoUri = env.getProperty("spring.data.mongodb.uri");
		return MongoClients.create(mongoUri);
	}

	@PostConstruct
	public void init() {
		System.out.println("Mongo URI = " + env.getProperty("spring.data.mongodb.uri"));
	}

}
