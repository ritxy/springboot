package com.rim.jpa.mariadb.exceptions;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class BookException extends Exception implements Serializable {

	private static final long serialVersionUID = -4594813649377934812L;
	private static final Logger logger = LogManager.getLogger(BookException.class);

	public String bookNotFoundException(Integer bookId) {
		logger.error("The book with the id {} doesn't exist", bookId);
		return "The book couldn't be found";
	}

	public String booksGreaterThanYearException(Integer year) {
		logger.error("There is no books for the year {}", year);
		return "There is no books for the selected year";
	}

}
