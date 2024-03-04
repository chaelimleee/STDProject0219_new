package com.javateam.STDProject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.javateam.STDProject.domain.StudentVO;

public interface StudentDao extends PagingAndSortingRepository<StudentVO, Integer>{
	
	Page<StudentVO> findAll(Pageable pageable);
	
	public void insert(StudentVO vo);
	public void update(StudentVO vo);
	public void delete(int seq);
	
	public void cnt(int seq);
	public int totalCount();
	public int totalCount2();
	
	StudentVO getBoard(int seq);
	List<StudentVO> getBoardList() ;
	List<StudentVO> getBoardList(StudentVO vo);
	List<StudentVO> getBoardList2(StudentVO vo);
}
