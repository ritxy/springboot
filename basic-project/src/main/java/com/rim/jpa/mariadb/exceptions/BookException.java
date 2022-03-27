package com.rim.jpa.mariadb.exceptions;

import java.io.Serializable;
import java.text.MessageFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class BookException extends Exception implements Serializable {

	private static final long serialVersionUID = -4594813649377934812L;
	private static final Logger logger = LogManager.getLogger(BookException.class);

	public String booksByYearException(Integer year) {
		String message = MessageFormat.format("Any book could be found for the year {0}", year);
		logger.error(message);
		return message;
	}

	public String bookNotFoundException(Integer bookId) {
		String message = MessageFormat.format("The book with the id {0} doesn{1}t exist", bookId, "'");
		logger.error(message);
		return message;
	}

	public String booksGreaterThanYearException(Integer year) {
		String message = MessageFormat.format("There is no books for the year {0}", year);
		logger.error(message);
		return message;
	}

}
