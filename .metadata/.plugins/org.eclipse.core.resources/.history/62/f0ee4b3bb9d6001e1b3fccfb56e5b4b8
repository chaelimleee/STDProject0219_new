package com.javateam.STDProject.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.STDProject.domain.StudentVO;
import com.javateam.STDProject.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repo ;
	
//	public Page<StudentVO> findAllByPaging(int page, int limit) {
//    	
//    	Pageable pageable = PageRequest.of(page-1, limit);
//
//    	return repo.findAll(pageable);
//    }
	
	public void update(StudentVO vo) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int seq) {
		// TODO Auto-generated method stub
		
	}

	public StudentVO getBoard(int seq) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<StudentVO> getBoardList(StudentVO vo) {
//		return dao.getBoardList(vo);
//	}

	public List<StudentVO> getBoardList(StudentVO vo) {
		// TODO Auto-generated method stub
		return repo.getBoardList(vo);
	}

//	public Iterable<StudentVO> findAll(Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	//페이징처리.
//	public Page<StudentVO> findAllByPaging(int page, int limit) {
//    	
//    	Pageable pageable = PageRequest.of(page-1, limit);
//
//    	return dao.findAll(pageable);
//    }

//	public Page<StudentVO> findAll(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public void cnt(int seq) {
		// TODO Auto-generated method stub
		
	}

	public int totalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int totalCount2() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<StudentVO> getBoardList2(StudentVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

//@Override
//public List<StudentVO> getBoardList(StudentVO vo) {
//	// TODO Auto-generated method stub
//	return null;
//}

}
