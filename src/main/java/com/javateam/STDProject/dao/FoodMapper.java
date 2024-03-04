/**
 * 
 */
package com.javateam.STDProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Role;

import com.javateam.STDProject.domain.FoodVO;
import com.javateam.STDProject.domain.Users;


/**
 * mapper
 * 
 * @author javateam
 *
 */
public interface FoodMapper {
	
	public void insert(FoodVO vo);
	public void update(FoodVO vo);
	public void delete(int id);
	
	public void cnt(int seq);
	public int totalCount();
	public int totalCount2();
	
	FoodVO getFoodList(int id); // select 번호를 받아서 찾아줌. 검색!
	List<FoodVO> getFoodAllList(FoodVO vo); // select 모든 정보를 리스트를 통해서 보여줌.
	List<FoodVO> getFoodByFoodNameList(String name);
	List<FoodVO> getFoodList();
	List<FoodVO> getFoodList(FoodVO vo);
	List<FoodVO> getFoodList2(FoodVO vo);

}