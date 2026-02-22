package com.comp307.lab2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comp307.lab2.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	Optional<Book> findByIsbn(String isbn);
	 List<Book> findByAuthorContainingIgnoreCase(String author);
	 List<Book> findByTitleContainingIgnoreCase(String title);
	 @Query("SELECT b FROM Book b WHERE b.price BETWEEN :minPrice AND :maxPrice")
	 List<Book> findByPriceRange(@Param("minPrice") Double minPrice,
	 @Param("maxPrice") Double maxPrice);
	 boolean existsByIsbn(String isbn);
}


