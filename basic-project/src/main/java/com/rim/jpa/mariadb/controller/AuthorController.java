package com.rim.jpa.mariadb.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rim.jpa.mariadb.dto.AuthorWithBooksDTO;
import com.rim.jpa.mariadb.model.Author;
import com.rim.jpa.mariadb.model.Book;
import com.rim.jpa.mariadb.repository.AuthorsRepository;
import com.rim.jpa.mariadb.repository.BooksRepository;
import com.rim.jpa.mariadb.utils.StringsUtils;

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {

	private static final Logger logger = LogManager.getLogger(AuthorController.class);

	@Autowired
	AuthorsRepository authorsRepository;

	@Autowired
	BooksRepository booksRepository;
	
	@Autowired
	StringsUtils stringsUtils;

	@GetMapping(path = "/", produces = "application/json")
	public List<Author> getAllAuthors() {
		logger.info("getAllAuthors");

		return authorsRepository.findAll();
	}
	
	@PostMapping(path = "/insert", produces = "application/json")
	public ResponseEntity<Object> insertAuthor(@RequestBody AuthorWithBooksDTO authorWithBooksDTO) {
		logger.info("getAllAuthors");

		Author author = authorWithBooksDTO.getAuthor();
		List<Book> listOfBooks = authorWithBooksDTO.getListOfBooks();
		author.setListOfBooks(listOfBooks);

		authorsRepository.saveAndFlush(author);

		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@PostMapping(path = "/insertAuthorWithBooks", produces = "application/json")
	public ResponseEntity<Object> insertAuthorWithBooks(@RequestBody AuthorWithBooksDTO authorWithBooksDTO) {
		logger.info("insertAuthorWithBooks");
		

		Author author = authorWithBooksDTO.getAuthor();
		List<Book> listOfBooks = authorWithBooksDTO.getListOfBooks();
		for (Book book : listOfBooks) {
			book.setAuthor(author);
		}
		author.setListOfBooks(listOfBooks);

		authorsRepository.flush();
		authorsRepository.save(author);

		return ResponseEntity.ok(Boolean.TRUE);
	}

}
