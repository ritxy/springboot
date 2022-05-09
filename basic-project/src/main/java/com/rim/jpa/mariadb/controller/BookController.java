package com.rim.jpa.mariadb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rim.jpa.mariadb.dto.AuthorWithBooksDTO;
import com.rim.jpa.mariadb.dto.BookDTO;
import com.rim.jpa.mariadb.exceptions.BookException;
import com.rim.jpa.mariadb.model.Author;
import com.rim.jpa.mariadb.model.Book;
import com.rim.jpa.mariadb.repository.AuthorsRepository;
import com.rim.jpa.mariadb.repository.BooksRepository;
import com.rim.jpa.mariadb.service.impl.BooksService;

@RestController
@RequestMapping(path = "/books")
public class BookController {

	private static final Logger logger = LogManager.getLogger(BookController.class);

	@Autowired
	AuthorsRepository authorsRepository;
	
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

	@GetMapping(path = "/id/{id}", produces = "application/json")
	public ResponseEntity<Object> getBookById(@PathVariable Integer id) {
		logger.info("getBookById");

		Book book = new Book();

		try {
			book = booksService.getBookById(id);

			if (book == null) {
				return new ResponseEntity<>(bookException.bookNotFoundException(id), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error("getBookById for the id {}, Exception: {}", id, ExceptionUtils.getStackTrace(e));
		}

		return ResponseEntity.ok(book);
	}

	@GetMapping(path = "/year/{year}", produces = "application/json")
	public ResponseEntity<Object> getAllBooksByYear(@PathVariable Integer year) {
		logger.info("getAllBooksByYear");

		List<Book> listOfBooks = new ArrayList<>();

		try {
			listOfBooks = booksService.getAllBooksByYear(year);

			if (listOfBooks.isEmpty()) {
				return new ResponseEntity<>(bookException.booksByYearException(year), HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error("getAllBooksByYear for the year {}, Exception: {}", year, ExceptionUtils.getStackTrace(e));
		}
		return ResponseEntity.ok(listOfBooks);
	}

	@GetMapping(path = "/year/gt/{year}", produces = "application/json")
	public ResponseEntity<Object> getAllBooksByGreaterThanYear(@PathVariable Integer year) {
		logger.info("getAllBooksByGreaterThanYear");
		List<Book> listOfBooks = new ArrayList<>();

		try {
			listOfBooks = booksService.getAllBooksByGreaterThanYear(year);

			if (listOfBooks.isEmpty()) {
				return new ResponseEntity<>(bookException.booksGreaterThanYearException(year), HttpStatus.BAD_REQUEST);
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
	
	@PostMapping(path = "/insertAuthorwithBooks", consumes = "application/json")
	@Transactional
	public ResponseEntity<Object> insertAuthorWithBooks(@RequestBody AuthorWithBooksDTO authorWithBooksDTO) {
		logger.info("insertBook");

		Author author = authorWithBooksDTO.getAuthor();
		List<Book> listOfBooks = authorWithBooksDTO.getListOfBooks();
		author.setListOfBooks(listOfBooks);

		authorsRepository.saveAndFlush(author);
		
		for (Book bookDto : listOfBooks) {
			Book book = new Book();
			
			author.setAuthorId(author.getAuthorId());

			book.setBookName(bookDto.getBookName());
			book.setBookReleaseDate(bookDto.getBookReleaseDate());
			book.setAuthor(author);
			book.getAuthor().setAuthorId(author.getAuthorId());

			booksRepository.saveAndFlush(book);
		}

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

	// UPDATE METHODS
	@PutMapping(path = "/update/{bookId}", produces = "application/json")
	public ResponseEntity<Object> updateBook(@PathVariable("bookId") Integer bookId, @RequestBody BookDTO bookDto) {
		logger.info("updateBook");

		booksService.updateBook(bookId, bookDto);

		return ResponseEntity.ok(Boolean.TRUE);
	}

}
