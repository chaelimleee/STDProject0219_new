package com.javateam.STDProject.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.javateam.STDProject.domain.FoodVO;
import com.javateam.STDProject.domain.StudentVO;

public interface FoodRepository {
	
	public void insert(FoodVO vo);
	public void update(FoodVO vo);
	public void delete(int id);
	
	public void cnt(int seq);
	public int totalCount();
	public int totalCount2();
	
	FoodVO getFoodList(int id); // select 번호를 받아서 찾아줌. 검색!
	List<FoodVO> getFoodList(); // select 모든 정보를 리스트를 통해서 보여줌.
	List<FoodVO> getFoodAllList(FoodVO vo); // select 모든 정보를 리스트를 통해서 보여줌.
	List<FoodVO> getFoodByFoodNameList(String name);
	List<FoodVO> getFoodList(FoodVO vo);
	List<FoodVO> getFoodList2(FoodVO vo);
}
