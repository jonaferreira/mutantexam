package com.ml.mutantexam;

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
public class MutantexamApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(MutantexamApplication.class);
	
	public static void main(String[] args) {
		logger.info("--Application Started--");
		
		SpringApplication.run(MutantexamApplication.class, args);
	}

}
