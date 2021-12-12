package com.rim.jpa.mariadb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJpaMariaDbApplication {

	private static final Logger logger = LogManager.getLogger(SpringBootJpaMariaDbApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaMariaDbApplication.class, args);

		logger.debug("Debugging log");
		logger.info("Info log");
		logger.warn("Hey, This is a warning!");
		logger.error("Oops! We have an Error. OK");
		logger.fatal("Damn! Fatal error. Please fix me.");
	}

}
