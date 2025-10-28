package com.bikram.Digital_Library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bikram.Digital_Library.entities.Book;
import com.bikram.Digital_Library.entities.Genre;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	// if we want to search by any field of this particular entity class itself, we don't need to write query ourselves
	// findBy<fieldName> ----> query automatically created, all implementation is done by spring
	List<Book> findByBookName(String bookName);
	
	List<Book> findByGenre(Genre genre);
	
	// if we want to search by any other field which is not present directly in this particular entity class itself, then we need to write query ourselves
	@Query("select b from Book b where b.author.authorId = :aid")
	List<Book> findByAuthorId(@Param("aid") int authorId);
	
	
}
