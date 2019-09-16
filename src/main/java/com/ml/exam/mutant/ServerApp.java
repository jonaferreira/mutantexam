package com.ml.exam.mutant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class to initialize spring boot application.
 * 
 * @author Jonathan Ivan Ferreira
 */
@SpringBootApplication
public class ServerApp {
	
	private static final Logger logger = LoggerFactory.getLogger(ServerApp.class);
	
	public static void main(String[] args) {
		logger.info("--Application Started--");
		
		SpringApplication.run(ServerApp.class, args);
	}
}
