package com.javateam.STDProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javateam.STDProject.domain.StudentVO;

@Service
public interface StudentService {
	
	public void insert(StudentVO vo); // insert
	public void update(StudentVO vo); // update
	public void delete(int seq); // delete 번호를 받아서 삭제 함.

	StudentVO getBoard(int seq); // select 번호를 받아서 찾아줌. 검색!
	List<StudentVO> getBoardList(); // select 모든 정보를 리스트를 통해서 보여줌.
	List<StudentVO> getBoardList(StudentVO vo);
} //