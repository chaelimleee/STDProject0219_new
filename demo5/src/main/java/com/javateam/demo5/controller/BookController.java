package com.javateam.demo5.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javateam.demo5.domain.Book;
import com.javateam.demo5.service.BookService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BookController {
	
	// log 객체
	private static final Logger log 
		= LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	// @RequestMapping(value="/books", method=RequestMethod.GET)
	@GetMapping("/books")
	public String requestBookList(Model model,
				HttpServletRequest request,
				@RequestParam(defaultValue ="1") int page) {
		
		log.info("/books 도서 목록"); // 콘솔창
		
		List<Book> list = bookService.getAllBookList();
		model.addAttribute("bookList", list); // 인자 생성.
//		return "books";
		return "books_bootstrap";
	}
}

