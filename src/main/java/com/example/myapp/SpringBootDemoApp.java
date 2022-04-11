package com.example.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableCaching
@EnableJpaRepositories(basePackages = "com.example.myapp.mysql")
@EnableMongoRepositories(basePackages = "com.example.myapp.mongodb")
@SpringBootApplication
public class SpringBootDemoApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApp.class, args);
	}

}
