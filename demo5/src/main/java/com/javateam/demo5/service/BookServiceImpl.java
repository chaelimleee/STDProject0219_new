package com.javateam.demo5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javateam.demo5.domain.Book;
import com.javateam.demo5.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired // 스프링 컨테이너가 자동적으로 인스턴스(객체)를 생성 
	private BookRepository bookRepository;

//	@Transactional(readOnly=true) - 읽기만 함.
	@Override
	public List<Book> getAllBookList() {
		return bookRepository.getAllBookList();
	}

}
