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

import com.javateam.STDProject.domain.FoodVO;
import com.javateam.STDProject.domain.StudentVO;
import com.javateam.STDProject.repository.FoodDaoImpl;
import com.javateam.STDProject.repository.FoodRepository;
import com.javateam.STDProject.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FoodService {
	
	@Autowired
	private FoodRepository foodRepo ;
	
	public List<FoodVO> getFoodList(){
		return foodRepo.getFoodList();
	}
	
	public List<FoodVO> getFoodList(FoodVO vo){
		log.info("푸드 서비스 왔나 확인");
		return foodRepo.getFoodList(vo);
	}
	public List<FoodVO> getFoodAllList(FoodVO vo){
		log.info("푸드 서비스 왔나 확인");
		return foodRepo.getFoodAllList(vo);
	}
	
	

}
