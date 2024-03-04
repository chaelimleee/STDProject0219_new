package com.javateam.demo6.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javateam.demo6.domain.Book;
import com.javateam.demo6.service.BookService;

@Controller
public class BookController {
	
	// log 객체
	private static final Logger log 
		= LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	// @RequestMapping(value="/books", method=RequestMethod.GET)
	@GetMapping("/books")
	public String requestBookList(Model model) {
		
		log.info("/books 도서 목록");
		
		List<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list);
		
		return "books";
//		return "books_bootstrap";
	}

}