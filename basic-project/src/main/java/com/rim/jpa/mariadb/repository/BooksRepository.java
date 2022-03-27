package com.rim.jpa.mariadb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rim.jpa.mariadb.model.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
	Book findByBookId(Integer id);

	@Query("select b from Book b where YEAR(b.bookReleaseDate) = ?1")
	List<Book> findByYear(Integer year);

	@Query("select b from Book b where YEAR(b.bookReleaseDate) >= ?1")
	List<Book> findByGreaterThanYear(Integer year);

	int removeByBookNameContaining(String bookName);

//	@Query("delete from Book b where b.bookName like CONCAT('%', :bookName, '%')")
//	int deleteByBookNameLikeContainin(@Param("bookName") String bookName);
}
