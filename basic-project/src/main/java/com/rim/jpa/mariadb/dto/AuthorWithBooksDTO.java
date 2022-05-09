package com.rim.jpa.mariadb.dto;

import java.io.Serializable;
import java.util.List;

import com.rim.jpa.mariadb.model.Author;
import com.rim.jpa.mariadb.model.Book;

public class AuthorWithBooksDTO implements Serializable {

	private static final long serialVersionUID = 3243731138818635441L;

	private Author author;
	private List<Book> listOfBooks;

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<Book> getListOfBooks() {
		return listOfBooks;
	}

	public void setListOfBooks(List<Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}

	@Override
	public String toString() {
		return "AuthorWithBooksDTO [author=" + author + ", listOfBooks=" + listOfBooks + "]";
	}

}
