package com.comp307.lab2.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.comp307.lab2.model.Book;
import com.comp307.lab2.service.BookService;

@Controller
public class HomeController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/")
	public String listBooks(Model model) {
		 List<Book> books = bookService.getAllBooks();
		 model.addAttribute("books", books);
		 model.addAttribute("totalBooks", books.size());
		 return "list";
		 
	}
	// 2. This handles the "Add New" button (http://localhost:8080/books/new)
    @GetMapping("/books/new")
    public String showAddForm(Model model) {
         // Create a blank book object so the form has something to bind to
         model.addAttribute("book", new Book()); 
         return "add"; // This MUST match your add.html filename
}
    @GetMapping("/books/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        // 1. You must get the specific book by its ID from the database
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        
        // 2. Pass that single book to the model
        model.addAttribute("book", book);
        
        // 3. Return the edit template
        return "edit"; 
    }
    }

