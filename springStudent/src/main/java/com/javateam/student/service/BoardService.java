package com.javateam.student.service;

import java.util.List;

import com.javateam.student.domain.BoardVO;

// select , insert, update, delete

public interface BoardService {
	public void insert(BoardVO vo); // insert
	public void update(BoardVO vo); // update
	public void delete(int seq); // delete 번호를 받아서 삭제 함.

	BoardVO getBoard(int seq); // select 번호를 받아서 찾아줌. 검색!
	List<BoardVO> getBoardList(BoardVO vo); // select 모든 정보를 리스트를 통해서 보여줌.
}


