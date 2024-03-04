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

import com.javateam.STDProject.domain.StudentVO;
import com.javateam.STDProject.paging.PagingJpaDAO;
import com.javateam.STDProject.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StudentController {
	
	private int page = 0;
	private int afterPage = 0;
	
	@Autowired
	private  StudentService service;
	
	@Autowired
	private PagingJpaDAO paingdao;

//	StudentController(StudentService service){
//		this.service = service;
//	}
	
	@RequestMapping("/")
	public String home() {
		
		return "redirect:/main";
//		return "redirect:/health";
	}
	
	@RequestMapping("/ListPaging")
	public String pagingHome() {
		
		return "redirect:/paging?page=1";
		
	}
	
	// 다음페이지.
		@RequestMapping("/ListPaging/afterPage")
		public String pagingAfterStudent() {
			
			if(page == afterPage) {
				page = afterPage;
			}else {
				page = page + 1 ;
			}
			System.out.println("after page 값 >>  "+ page);
			
			return "redirect:/paging?page="+page;
			
		}
		
		// 이전페이지.
		@RequestMapping("/ListPaging/beforePage")
		public String pagingbeforeStudent() {
			
			if(page > 1) {
				page = page - 1 ;
			}else {
				page = 1;
			}
			System.out.println("before page 값 >>  "+ page);
			
			return "redirect:/paging?page="+page;
			
		}
	
	
	@RequestMapping("/paging")
	public String paging(@RequestParam("page") int page, Model model) {
		
		log.info("페이징 컨트롤러!>>>>");
		
		Page<StudentVO> pageList = paingdao.findAll(PageRequest.of(page - 1 , 10));
		List<StudentVO> list = pageList.getContent();
		
		model.addAttribute("total_page", pageList.getTotalPages());
		if(page == pageList.getTotalPages()) {
			afterPage = page;
			pagingAfterStudent();
		}
        model.addAttribute("curr_page", page);
        model.addAttribute("list", list);
		
		return "getBoardListPaging";
	}//
	
	@RequestMapping("/health")
	public String health() {
		
		return "/healthMain";
	}
	
	@RequestMapping("/main")
	public String main() {
		
		return "/main";
	}
	
	
	@GetMapping("/studentList")
	public String getBoardList(Model model, StudentVO vo) {
		
		System.out.println(vo);
		log.info("확인1: " + vo);

		System.out.println("확인1");
		 List<StudentVO> list = service.getBoardList(vo);
		 System.out.println("확인2 list : "+ list);
		 System.out.println("확인2");
//
//		 result.addObject("studentList", list);
//		 result.setViewName("/getBoardList");
		
		model.addAttribute("list", list );
		
		 return "/getBoardList";
	}
	
//	@GetMapping("/studentList")
//	public ModelAndView getBoardList(StudentVO vo) {
//		
//		ModelAndView result = new ModelAndView();
//		List<StudentVO> list = service.getBoardList(vo);
//		
//		result.addObject("studentList", list);
//		result.setViewName("/getBoardList");
//		
//		return result;
//	}
	

}
