package com.rim.jpa.mariadb.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rim.jpa.mariadb.dto.BookDTO;
import com.rim.jpa.mariadb.exceptions.BookException;
import com.rim.jpa.mariadb.model.Author;
import com.rim.jpa.mariadb.model.Book;
import com.rim.jpa.mariadb.repository.BooksRepository;
import com.rim.jpa.mariadb.service.impl.BooksService;

@RestController
@RequestMapping(path = "/books")
public class BookController {

	private static final Logger logger = LogManager.getLogger(BookController.class);

	@Autowired
	BooksRepository booksRepository;

	@Autowired
	BooksService booksService;

	@Autowired
	BookException bookException;

	// GET METHODS
	@GetMapping(path = "/", produces = "application/json")
	public ResponseEntity<Object> getAllBooks() {
		logger.info("getAllBooks");

		return ResponseEntity.ok(booksRepository.findAll());
	}

	@GetMapping(path = "/year/{year}", produces = "application/json")
	public ResponseEntity<Object> getAllBooksByYear(@PathVariable Integer year) {
		logger.info("getAllBooksByYear");

		return ResponseEntity.ok(booksService.getAllBooksByYear(year));
	}

	@GetMapping(path = "/year/gt/{year}", produces = "application/json")
	public ResponseEntity<Object> getAllBooksByGreaterThanYear(@PathVariable Integer year) {
		logger.info("getAllBooksByGreaterThanYear");
		List<Book> listOfBooks = new ArrayList<>();

		try {
			listOfBooks = booksService.getAllBooksByGreaterThanYear(year);

			if (listOfBooks.isEmpty()) {
				String a = bookException.booksGreaterThanYearException(year);
				return new ResponseEntity(a, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error("getAllBooksByGreaterThanYear for the year {}, Exception: {}", year, ExceptionUtils.getStackTrace(e));
		}

		return ResponseEntity.ok(booksService.getAllBooksByGreaterThanYear(year));
	}

	// INSERT METHODS
	@PostMapping(path = "/insert", consumes = "application/json")
	public ResponseEntity<Object> insertBook(@RequestBody BookDTO bookDto) {
		logger.info("insertBook");

		Book book = new Book();
		Author author = new Author();

		author.setAuthorId(bookDto.getAuthor().getAuthorId());

		book.setBookName(bookDto.getBookName());
		book.setBookReleaseDate(bookDto.getBookReleaseDate());
		book.setAuthor(author);
		book.getAuthor().setAuthorId(author.getAuthorId());

		booksRepository.saveAndFlush(book);

		return ResponseEntity.ok(Boolean.TRUE);
	}

	// DELETE METHODS
	@DeleteMapping(path = "/delete/{bookId}", produces = "application/json")
	public ResponseEntity<Object> deleteBookById(@PathVariable Integer bookId) {
		logger.info("deleteBook");

		booksRepository.deleteById(bookId);

		return ResponseEntity.ok(Boolean.TRUE);
	}

	@DeleteMapping(path = "/delete/byname", produces = "application/json")
	public ResponseEntity<Object> deleteBookByNameLike(@RequestBody BookDTO bookDto) {
		logger.info("deleteBook");

		Book book = new Book();
		book.setBookName(bookDto.getBookName());

		return ResponseEntity.ok(booksService.deleteByBookNameLike(bookDto));
	}

}
