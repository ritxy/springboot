package com.rim.jpa.mariadb.dto;

import java.io.Serializable;
import java.sql.Date;

public class AuthorDTO implements Serializable {

	private static final long serialVersionUID = 3458855568084713149L;

	private Integer authorId;
	private String authorName;
	private String authorSurname;
	private Date authorBirthday;

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
		return "AuthorDTO [authorId=" + authorId + ", authorName=" + authorName + ", authorSurname=" + authorSurname
				+ ", authorBirthday=" + authorBirthday + "]";
	}

}
