package com.javateam.foodCrawlingDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javateam.foodCrawlingDemo.domain.FoodVO;

public interface FoodRepository extends JpaRepository<FoodVO, Long> {
	
//	String save(FoodVO foodvo);
	

}