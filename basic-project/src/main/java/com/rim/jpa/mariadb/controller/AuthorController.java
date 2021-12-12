package com.rim.jpa.mariadb.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rim.jpa.mariadb.model.Author;
import com.rim.jpa.mariadb.repository.AuthorsRepository;
import com.rim.jpa.mariadb.utils.StringsUtils;

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {

	private static final Logger logger = LogManager.getLogger(AuthorController.class);

	@Autowired
	AuthorsRepository authorsRepository;

	@Autowired
	StringsUtils stringsUtils;

	@GetMapping(path = "/", produces = "application/json")
	public List<Author> getAllAuthors() {
		logger.info("getAllAuthors");

		return authorsRepository.findAll();
	}

}
