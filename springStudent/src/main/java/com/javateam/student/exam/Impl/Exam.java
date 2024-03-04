package com.javateam.student.exam.Impl;

import java.util.List;

public interface Exam {
	
	public void insert(ExamVO vo);
	public void update(ExamVO vo);
	public int delete(int sno);
	
	public int summ();
	
	ExamVO getExam(int sno);
	List<ExamVO> getExamList(ExamVO vo);

}
