package com.rim.jpa.mariadb.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
public class Author implements Serializable {

	private static final long serialVersionUID = -6481024412776299969L;

	@Id
	@Column(name = "author_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer authorId;

	@Column(name = "author_name")
	private String authorName;

	@Column(name = "author_surname")
	private String authorSurname;

	@Column(name = "author_birthday")
	private Date authorBirthday;

	// We can use CascadeType.REFRESH or CascadeType.PERSIST if we want to make an
	// INSERT
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "book_author_fk")
	private List<Book> listOfBooks = new ArrayList<>();

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorSurname() {
		return authorSurname;
	}

	public void setAuthorSurname(String authorSurname) {
		this.authorSurname = authorSurname;
	}

	public Date getAuthorBirthday() {
		return authorBirthday;
	}

	public void setAuthorBirthday(Date authorBirthday) {
		this.authorBirthday = authorBirthday;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + ", authorSurname=" + authorSurname + ", authorBirthday=" + authorBirthday + "]";
	}

	public List<Book> getListOfBooks() {
		return listOfBooks;
	}

	public void setListOfBooks(List<Book> listOfBooks) {
		if(listOfBooks == null) {
			listOfBooks = new ArrayList<>();	
		}
		this.listOfBooks.addAll(listOfBooks);
	}

}
