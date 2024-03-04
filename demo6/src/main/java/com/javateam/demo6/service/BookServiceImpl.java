package com.javateam.demo6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javateam.demo6.domain.Book;
import com.javateam.demo6.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired // 스프링 컨테이너가 자동적으로 인슽컨스(객체)를 생성 
	private BookRepository bookRepository;

	// @Transactional(readOnly=true)
	@Override
	public List<Book> getAllBookList() {
		return bookRepository.getAllBookList();
	}

}
