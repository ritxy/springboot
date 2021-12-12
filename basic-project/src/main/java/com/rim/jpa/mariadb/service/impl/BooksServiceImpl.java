package com.rim.jpa.mariadb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rim.jpa.mariadb.dto.BookDTO;
import com.rim.jpa.mariadb.model.Book;
import com.rim.jpa.mariadb.repository.BooksRepository;

@Service
public class BooksServiceImpl implements BooksService {

	private static final Logger logger = LogManager.getLogger(BooksServiceImpl.class);

	@Autowired
	BooksRepository booksRepository;

	@Override
	public List<Book> getAllBooksByYear(Integer year) {
		logger.info("getAllBooksByYear: {}", year);
		List<Book> list = new ArrayList<>();

		try {
			list = booksRepository.findByYear(year);
		} catch (Exception e) {
			logger.error("Error getAllBooksByYear, Exception: {}", ExceptionUtils.getStackTrace(e));
		}

		return list;
	}

	@Override
	public List<Book> getAllBooksByGreaterThanYear(Integer year) {
		logger.info("getAllBooksByGreaterThanYear: {}", year);
		List<Book> list = new ArrayList<>();

		try {
			list = booksRepository.findByGreaterThanYear(year);
		} catch (Exception e) {
			logger.error("Error getAllBooksByGreaterThanYear, Exception: {}", ExceptionUtils.getStackTrace(e));
		}

		return list;
	}

	@Override
	@Transactional
	public int deleteByBookNameLike(BookDTO bookDto) {
		logger.info("removeByBookNameLike: {}", bookDto.getBookName());
		int numberOfBooksDeleted = 0;

		try {
			numberOfBooksDeleted = booksRepository.removeByBookNameContaining(bookDto.getBookName());
		} catch (Exception e) {
			logger.error("Error getAllBooksByGreaterThanYear, Exception: {}", ExceptionUtils.getStackTrace(e));
		}

		return numberOfBooksDeleted;
	}

}
