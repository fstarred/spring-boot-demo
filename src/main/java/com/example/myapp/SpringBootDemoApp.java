package com.example.myapp;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.function.Consumer;
import java.util.function.Function;

@EnableAsync
@EnableCaching
@EnableJpaRepositories(basePackages = "com.example.myapp.mysql")
@EnableMongoRepositories(basePackages = "com.example.myapp.mongodb")
@SpringBootApplication
public class SpringBootDemoApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApp.class, args);
	}

	@Autowired
	Logger logger;

//	@Bean
//	public Consumer<String> readMessage() {
//		return o -> logger.info("received message: {}", o);
//	}

	/**
	 * process kafka message
	 * @return
	 */
	@Bean
	public Function<String, String> processMessage() {
		return o -> {
			logger.info("processing message: {}", o);
			return o.toUpperCase();
		};
	}

//	@Bean
//	public Supplier<String> sendEvents() {
//		return () -> {
//			logger.info("sending message to my-topic..");
//			return "hello foo";
//		};
//	}

}
