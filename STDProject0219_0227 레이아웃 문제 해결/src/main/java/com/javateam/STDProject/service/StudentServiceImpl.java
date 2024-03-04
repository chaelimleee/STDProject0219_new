package com.javateam.STDProject.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.javateam.STDProject.domain.StudentVO;
import com.javateam.STDProject.repository.StudentDao;

@Service
public class StudentServiceImpl implements StudentService{

	private JdbcTemplate template ;
//	
	public void setJdbcTemlate(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	@Autowired
	private StudentDao dao ;
	
	@Override
	public void insert(StudentVO vo) {
		dao.insert(vo);
		
	}

	@Override
	public void update(StudentVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int seq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StudentVO getBoard(int seq) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<StudentVO> getBoardList(StudentVO vo) {
//		return dao.getBoardList(vo);
//	}

	@Override
	public List<StudentVO> getBoardList(StudentVO vo) {
		// TODO Auto-generated method stub
		return dao.getBoardList(vo);
	}

	@Override
	public List<StudentVO> getBoardList() {
		// TODO Auto-generated method stub
		return null;
	}

//@Override
//public List<StudentVO> getBoardList(StudentVO vo) {
//	// TODO Auto-generated method stub
//	return null;
//}

}
