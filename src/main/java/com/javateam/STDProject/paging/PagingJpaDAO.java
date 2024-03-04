package com.javateam.STDProject.paging;

import com.javateam.STDProject.domain.StudentVO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagingJpaDAO extends PagingAndSortingRepository<StudentVO, Integer> {
	 
//    Iterable<StudentVO> findAll(Sort sort);
    Page<StudentVO> findAll(Pageable pageable);
    StudentVO findById(int id);

   
} 