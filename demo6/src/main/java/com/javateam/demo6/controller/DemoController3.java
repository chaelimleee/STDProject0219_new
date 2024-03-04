package com.javateam.demo6.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javateam.demo6.domain.Book;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DemoController3 {
	
	private static final Logger log
		= LoggerFactory.getLogger(DemoController3.class);
	
	@GetMapping("/demo3")
	@ResponseBody
	public String demo(Book book) {
		
		log.info("demo3");
		
		String msg = "";
		
		log.info("book : " + book);
		
		
		return book.toString();
	}
}
