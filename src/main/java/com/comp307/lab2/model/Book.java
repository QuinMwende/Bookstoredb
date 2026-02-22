package com.comp307.lab2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")

public class Book {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 @NotBlank(message = "Title is required")
	 @Size(min = 1, max = 200, message = "Title to be between 1 and 200 characters")
	 @Column(nullable = false)
	 private String title;
	 
	 @NotBlank(message = "Author is required")
	 @Size(min = 2, max = 100, message = "Author name between 2 and 100 characters")
	 @Column(nullable = false)
	 private String author;
	 
	 @NotBlank(message = "ISBN is required")
	 @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "Invalid ISBN format")
	 @Column(unique = true, nullable = false)
	 private String isbn;
	 
	 @Column(nullable = false)
	 private String category;
	 
	 @Min(value = 0, message = "Price cannot be negative")
	 @Column(nullable = false)
	 private Double price;
	 
	 @PastOrPresent(message = "Publication date cannot be in the future")
	 @Column(name = "publicationdate")
	 private LocalDate publicationDate;
	 
	 @Column(name = "createdat")
	 private LocalDate createdAt;
	 
	 @Column(name = "updatedat")
	 private LocalDate updatedAt;
	 
	 @PrePersist
	 protected void onCreate() {
		 createdAt = LocalDate.now();
		 updatedAt = LocalDate.now();
	 }
	 
	 @PreUpdate
	 protected void onUpdate() {
		 updatedAt = LocalDate.now();
	 }
	 
	 public String getAuthor() {
		 return author;
	 }
	 
	 public Long getId() {
		 return id;
	}
	 
	 public void setId(Long id) {
		 this.id = id;
	 }
	 
	 public String getIsbn() {
		 return isbn;
	 }
	 
	 public void setIsbn(String isbn) {
		 this.isbn = isbn;
	 }
	 
	 public Double getPrice() {
		 return price;
	 }
	 
	 public void setPrice(Double price) {
		 this.price = price;
	 }
	 
	 public LocalDate getPublicationDate() {
		 return publicationDate;
	 }
	 
	 public void setPublicationDate(LocalDate publicationDate) {
		 this.publicationDate = publicationDate;
	 }
	 
	 public LocalDate getCreatedAt() {
		 return createdAt;
	 }
	 
	 public void setCreatedAt(LocalDate createdAt) {
		 this.createdAt = createdAt;
	 }
	 
	 public LocalDate getUpdatedAt() {
		 return updatedAt;
	 }
	 
	 public void setUpdatedAt(LocalDate updatedAt) {
		 this.updatedAt = updatedAt;
	 }
	 
	 public void setTitle(String title) {
		 this.title = title;
	 }
	 
	 public void setAuthor(String author) {
		 this.author = author;
	 }
	 
	 public String getTitle() {
		 return title;
	 }
	 
	 public String getCategory() {
		 return category;
	 }
	public void setCategory(String category) {
		 this.category = category;
		}
	 }

	
