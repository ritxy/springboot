package com.rim.jpa.mariadb.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "books")
public class Book implements Serializable {

	private static final long serialVersionUID = 6421213138289628503L;

	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;

	@Column(name = "book_name")
	private String bookName;

	@Column(name = "book_release_date")
	private Date bookReleaseDate;

	// We can use CascadeType.REFRESH or CascadeType.PERSIST if we want to make an
	// INSERT
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "book_author_fk")
	// With this line avoid the loop reference at print the JSON
	@JsonIgnoreProperties({"listOfBooks", "hibernateLazyInitializer", "handler"}) 
	private Author author = new Author();

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

}
