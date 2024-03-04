package com.javateam.STDProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.STDProject.domain.StudentVO;
import com.javateam.STDProject.repository.PagingJpaDAO;

@Service
@Transactional
public class PagingJpaService {
	
	@Autowired
	PagingJpaDAO dao;

    public List<StudentVO> findAll(String sort) {
    	
    	Direction direction = sort.equals("오름차순") ?  Direction.ASC : Direction.DESC;
    	
    	return (List<StudentVO>) dao.findAll(Sort.by(new Order(direction, "seq")));//번호을 기준으로 오름차순. 가나다라..
    }
    
    public Page<StudentVO> findAllByPaging(int page, int limit) { // page : 페이지 번호 , limit : 페이지당 항목 수.
    	
    	Pageable pageable = PageRequest.of(page-1, limit);//PageRequest.of : 페이지 번호가 1부터 시작하는 경우. 내부적으로 0부터 시작하는 인덱스로 맞춰줌. 

    	return dao.findAll(pageable);
    }
   
    public StudentVO findById(int id) {
    	
    	return dao.findById(id);
    }

}