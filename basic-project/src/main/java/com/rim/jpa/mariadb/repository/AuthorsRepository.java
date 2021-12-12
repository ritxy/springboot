package com.rim.jpa.mariadb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rim.jpa.mariadb.model.Author;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Integer> {

}
