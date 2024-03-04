package com.javateam.STDProject.repository;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javateam.STDProject.controller.FoodController;
import com.javateam.STDProject.dao.FoodMapper;
import com.javateam.STDProject.dao.StudentMapper;
import com.javateam.STDProject.domain.FoodVO;
import com.javateam.STDProject.domain.StudentVO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Primary
@Slf4j
public class FoodDaoImpl implements FoodRepository{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
	    this.sqlSession = (SqlSessionTemplate) sqlSession;
	 }
	
    @Autowired
    public void setDataSource(DataSource dataSource) {
		new JdbcTemplate(dataSource);
    }

	@Override
	public void insert(FoodVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(FoodVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cnt(int seq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int totalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalCount2() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FoodVO getFoodList(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FoodVO> getFoodList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FoodVO> getFoodList(FoodVO vo) {
		List<FoodVO> li = new ArrayList<FoodVO> ();
		FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);
		li = foodMapper.getFoodList();
		
		return li;
	}
	@Override
	public List<FoodVO> getFoodAllList(FoodVO vo) {
		List<FoodVO> li = new ArrayList<FoodVO> ();
		FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);
		li = foodMapper.getFoodAllList(vo);
		
		return li;
	}

	@Override
	public List<FoodVO> getFoodList2(FoodVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FoodVO> getFoodByFoodNameList(String name) {
		List<FoodVO> li = new ArrayList<FoodVO> ();
		FoodMapper foodMapper = sqlSession.getMapper(FoodMapper.class);
		li = foodMapper.getFoodByFoodNameList(name);
		
		return li;
	}


}
