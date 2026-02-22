package com.comp307.lab2.controller;

import com.comp307.lab2.model.Book;
import com.comp307.lab2.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
	
	private final BookService bookService;
	public BookController(BookService bookService) {
	    this.bookService = bookService;
	}
	
	 @PostMapping
	 public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
		 Book createdBook = bookService.createBook(book);
		 return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
	 }	
	 
	 @GetMapping
	 public ResponseEntity<List<Book>> getAllBooks() {
		 List<Book> books = bookService.getAllBooks();
		 return ResponseEntity.ok(books);
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		 return bookService.getBookById(id)
				 .map(ResponseEntity::ok)
				 .orElse(ResponseEntity.notFound().build());
	 }
	 
	 @GetMapping("/isbn/{isbn}")
	 public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
		 return bookService.getBookByIsbn(isbn)
				 .map(ResponseEntity::ok)
				 .orElse(ResponseEntity.notFound().build());
	 }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Book> updateBook(@PathVariable Long id,
			 
			 @Valid @RequestBody Book bookDetails) {
		 try {
			 Book updatedBook = bookService.updateBook(id, bookDetails);
			 return ResponseEntity.ok(updatedBook);
		 } catch (IllegalArgumentException e) {
			 return ResponseEntity.notFound().build();
		 }
	 }
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		 try {
			 bookService.deleteBook(id);
			 return ResponseEntity.noContent().build();
		 } catch (IllegalArgumentException e) {
			 return ResponseEntity.notFound().build();
		 }
	 }
	 
	 @GetMapping("/search/author")
	 public ResponseEntity<List<Book>> searchByAuthor(@RequestParam String author)
	{
		 List<Book> books = bookService.searchByAuthor(author);
		 return ResponseEntity.ok(books);
	 }
	 
	 @GetMapping("/search/title")
	 public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title) {
		 List<Book> books = bookService.searchByTitle(title);
		 return ResponseEntity.ok(books);
	 }
	 
	 @GetMapping("/search/price")
	 public ResponseEntity<List<Book>> getBooksByPriceRange(
			 @RequestParam Double minPrice,
			 @RequestParam Double maxPrice) {
		 List<Book> books = bookService.getBooksByPriceRange(minPrice, maxPrice);
		 return ResponseEntity.ok(books);
	 }
}
