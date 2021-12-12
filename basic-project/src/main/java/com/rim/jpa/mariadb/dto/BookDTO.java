package com.rim.jpa.mariadb.dto;

import java.io.Serializable;
import java.sql.Date;

import com.rim.jpa.mariadb.model.Author;

public class BookDTO implements Serializable {

	private static final long serialVersionUID = 3458855568084713149L;

	private Integer bookId;
	private String bookName;
	private Date bookReleaseDate;
	private Author author;

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Date getBookReleaseDate() {
		return bookReleaseDate;
	}

	public void setBookReleaseDate(Date bookReleaseDate) {
		this.bookReleaseDate = bookReleaseDate;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "BookDTO [bookId=" + bookId + ", bookName=" + bookName + ", bookReleaseDate=" + bookReleaseDate + ", author=" + author + "]";
	}

}
