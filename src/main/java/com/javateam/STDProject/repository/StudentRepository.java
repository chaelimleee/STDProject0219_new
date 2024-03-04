package com.javateam.STDProject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.javateam.STDProject.domain.StudentVO;

public interface StudentRepository {
	
//	Page<StudentVO> findAll(Pageable pageable);
	
	public void insert(StudentVO vo);
	public void update(StudentVO vo);
	public void delete(int seq);
	
	public void cnt(int seq);
	public int totalCount();
	public int totalCount2();
	
	StudentVO getBoard(int seq); // select 번호를 받아서 찾아줌. 검색!
	List<StudentVO> getBoardList(); // select 모든 정보를 리스트를 통해서 보여줌.
	List<StudentVO> getBoardList(StudentVO vo);
	List<StudentVO> getBoardList2(StudentVO vo);
}
