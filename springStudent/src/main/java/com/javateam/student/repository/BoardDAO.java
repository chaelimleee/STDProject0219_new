package com.javateam.student.repository;

import java.util.List;

import com.javateam.student.domain.BoardVO;


public interface BoardDAO {
	
	public void insert(BoardVO vo);
	public void update(BoardVO vo);
	public void delete(int seq);
	
	public void cnt(int seq);
	public int totalCount();
	public int totalCount2();
	
	BoardVO getBoard(int seq);
	List<BoardVO> getBoardList(BoardVO vo) ;
	List<BoardVO> getBoardList2(BoardVO vo);
	

}
