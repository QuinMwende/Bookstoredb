package com.comp307.lab2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		 return "book/list";
	}
}
