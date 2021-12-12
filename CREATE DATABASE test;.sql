-- Database mariadb  Ver 15.1 Distrib 10.3.32-MariaDB, for debian-linux-gnu (x86_64) using readline 5.2

-- Create a database and user
CREATE DATABASE test;
SHOW DATABASES;
CREATE USER user@localhost IDENTIFIED BY 'passwd';
GRANT ALL PRIVILEGES ON *.* TO 'user'@'localhost' IDENTIFIED BY 'passwd';
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'user'@localhost;

-- Create tables and data
DROP TABLE IF EXISTS `authors`;

CREATE TABLE `authors` (
    `author_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Author id',
    `author_name` varchar(100) NOT NULL COMMENT 'Author name',
    `author_surname` varchar(255) NOT NULL COMMENT 'Author name',
    `author_birthday` date NOT NULL COMMENT 'Author birthday',
    PRIMARY KEY (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `authors` WRITE;
INSERT INTO `authors` VALUES (1,'William','Shakespeare','1564-04-23'),(2,'George','Orwell','1903-06-25'),(3,'Jules','Verne','1828-02-08');
UNLOCK TABLES;



DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Book id',
  `book_name` varchar(255) NOT NULL COMMENT 'Title of the book',
  `book_author_fk` int(11) NOT NULL COMMENT 'Author id',
  `book_release_date` date DEFAULT NULL COMMENT 'Publication date',
  PRIMARY KEY (`book_id`),
  KEY `books_FK` (`book_author_fk`),
  CONSTRAINT `books_FK` FOREIGN KEY (`book_author_fk`) REFERENCES `authors` (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `books` WRITE;
INSERT INTO `books` VALUES (1,'Hamlet',1,'1602-01-01'),(2,'Macbeth',1,'1606-01-01'),(3,'Romeo and Juliet',1,'1597-01-01'),(4,'Animal Farm',2,'1945-08-17'),(5,'Nineteen Eighty-Four',2,'1949-05-08'),(6,'Twenty Thousand Leagues Under the Seas',3,'1870-01-01'),(7,'Around the Moon',3,'1870-01-01');
UNLOCK TABLES;



DROP TABLE IF EXISTS `genres`;

CREATE TABLE `genres` (
  `genre_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Genre id',
  `genre_name` varchar(255) NOT NULL COMMENT 'Genre name',
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `genres` WRITE;
INSERT INTO `genres` VALUES (1,'social science fiction'),(2,'political fiction'),(3,'dystopian'),(4,'political satire'),(5,'shakespearean tragedy'),(6,'adventure'),(7,'science fiction');
UNLOCK TABLES;



DROP TABLE IF EXISTS `book_genre`;

CREATE TABLE `book_genre` (
    `book_genre_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id of the table book_genre',
    `book_id` int(11) NOT NULL COMMENT 'Id of a book',
    `genre_id` int(11) NOT NULL COMMENT 'Id of a genre',
    PRIMARY KEY (`book_genre_id`),
    KEY `book_genre_FK` (`book_id`),
    KEY `book_genre_FK_1` (`genre_id`),
    CONSTRAINT `book_genre_FK` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`),
    CONSTRAINT `book_genre_FK_1` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `book_genre` WRITE;
INSERT INTO `book_genre` VALUES (1,5,1),(2,5,2),(3,5,3),(4,4,4),(5,3,5),(6,2,5),(7,1,5),(8,6,6),(9,7,7),(10,7,6);
UNLOCK TABLES;
