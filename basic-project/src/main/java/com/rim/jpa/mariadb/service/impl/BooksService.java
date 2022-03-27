package com.rim.jpa.mariadb.service.impl;

import java.util.List;

import com.rim.jpa.mariadb.dto.BookDTO;
import com.rim.jpa.mariadb.model.Book;

public interface BooksService {
	Book getBookById(Integer bookId);

	List<Book> getAllBooksByYear(Integer year);

	List<Book> getAllBooksByGreaterThanYear(Integer year);

	int deleteByBookNameLike(BookDTO bookDto);

	Book updateBook(Integer bookId, BookDTO bookDto);
}
