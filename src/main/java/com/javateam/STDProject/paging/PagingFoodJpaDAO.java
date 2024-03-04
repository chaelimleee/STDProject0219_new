package com.javateam.STDProject.paging;

import com.javateam.STDProject.domain.FoodVO;
import com.javateam.STDProject.domain.StudentVO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingFoodJpaDAO extends PagingAndSortingRepository<FoodVO, Integer> {
	 

//    Iterable<FoodVO> findAllFood(Sort sort);
    Page<FoodVO> findAll(Pageable pageable);
    FoodVO findById(int id);
   
} 