package com.javateam.STDProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javateam.STDProject.domain.FoodVO;
import com.javateam.STDProject.domain.StudentVO;
import com.javateam.STDProject.paging.PagingFoodJpaDAO;
import com.javateam.STDProject.paging.PagingJpaDAO;
import com.javateam.STDProject.service.FoodService;
import com.javateam.STDProject.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FoodController {
	
	int page = 0;
	int afterPage = 0;
	
	@Autowired
	private  FoodService service;
	
	@Autowired
	private PagingFoodJpaDAO paingdao;

	
	@RequestMapping("/FoodListPasing")
	public String pagingHome() {
		
		return "redirect:/foodPaging?page=1";
		
	}
	
	// 다음페이지.
	@RequestMapping("/FoodListPasing/afterPage")
	public String pagingAfterHome() {
		
		if(page == afterPage) {
			page = afterPage;
		}else {
			page = page + 1 ;
		}
		System.out.println("after page 값 >>  "+ page);
		
		return "redirect:/foodPaging?page="+page;
		
	}
	
	// 이전페이지.
	@RequestMapping("/FoodListPasing/beforePage")
	public String pagingbeforeHome() {
		
		if(page > 1) {
			page = page - 1 ;
		}else {
			page = 1;
		}
		System.out.println("before page 값 >>  "+ page);
		
		return "redirect:/foodPaging?page="+page;
		
	}
	
	
	@RequestMapping("/foodPaging")
	public String paging(@RequestParam("page") int page, Model model) {
		try {
			
			Page<FoodVO> pageList = paingdao.findAll(PageRequest.of(page - 1 , 5));
			
			List<FoodVO> foodList = pageList.getContent();
			
			model.addAttribute("total_page", pageList.getTotalPages());
			if(page == pageList.getTotalPages()) {
				afterPage = page;
				pagingAfterHome();
			}
	        model.addAttribute("curr_page", page);
	        model.addAttribute("foodList", foodList);
	        
		}catch(Exception e) {
			log.error("페이징처리 중 에러 발생: ", e);
		}
		return "getFoodListPaging";
	}//
	
	@GetMapping("/foodList")
	public String getFoodList(Model model, FoodVO vo) {
		 
		List<FoodVO> list = service.getFoodAllList(vo);
		
		model.addAttribute("foodList", list );
		
		 return "/getFoodListPaging";
	}
}
