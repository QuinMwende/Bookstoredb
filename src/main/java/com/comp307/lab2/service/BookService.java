package com.comp307.lab2.service;

import com.comp307.lab2.model.Book;
import com.comp307.lab2.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

	private final BookRepository bookRepository ;
	public BookService(BookRepository bookRepository) {
	    this.bookRepository = bookRepository;
	}
	
	 public Book createBook(Book book) {
	 if (bookRepository.existsByIsbn(book.getIsbn())) {
	 throw new IllegalArgumentException("Book with ISBN " + book.getIsbn()
	+ " already exists");
	 }
	 	return bookRepository.save(book);
	 }
	 
	 @Transactional(readOnly = true)
	 public List<Book> getAllBooks() {
		 return bookRepository.findAll();
	 }
	 
	 @Transactional(readOnly = true) 
	 public Optional<Book> getBookById(Long id) {
		 return bookRepository.findById(id);
	 }
	 
	 @Transactional(readOnly = true)
	 public Optional<Book> getBookByIsbn(String isbn) {
		 return bookRepository.findByIsbn(isbn);
	 }
	 
	 public Book updateBook(Long id, Book bookDetails) {
		 return bookRepository.findById(id)
				 .map(existingBook -> {
					 // Check if ISBN is being changed and if it already exists
					 if (!existingBook.getIsbn().equals(bookDetails.getIsbn()) &&
							 bookRepository.existsByIsbn(bookDetails.getIsbn())) {
						 throw new IllegalArgumentException("Book with ISBN " +
	bookDetails.getIsbn() + " already exists");
					 }
					 
					 existingBook.setTitle(bookDetails.getTitle());
					 existingBook.setAuthor(bookDetails.getAuthor());
					 existingBook.setIsbn(bookDetails.getIsbn());
					 existingBook.setPrice(bookDetails.getPrice());

	existingBook.setPublicationDate(bookDetails.getPublicationDate());
	 				return bookRepository.save(existingBook);
				 })
				 .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + id));
	 }
	 public void deleteBook(Long id) {
		 if (!bookRepository.existsById(id)) {
			 throw new IllegalArgumentException("Book not found with id: " + id);
		 }
		 bookRepository.deleteById(id);
	 }
	 @Transactional(readOnly = true)
	 public List<Book> searchByAuthor(String author) {
		 return bookRepository.findByAuthorContainingIgnoreCase(author);
	 }
	 @Transactional(readOnly = true)
	 public List<Book> searchByTitle(String title) {
		 return bookRepository.findByTitleContainingIgnoreCase(title);
	 }
	 @Transactional(readOnly = true)
	 public List<Book> getBooksByPriceRange(Double minPrice, Double maxPrice) {
		 return bookRepository.findByPriceRange(minPrice, maxPrice);

	 }
}
