package com.javateam.STDProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javateam.STDProject.domain.StudentVO;
import com.javateam.STDProject.repository.StudentDao;
import com.javateam.STDProject.repository.StudentDaoImpl;
import com.javateam.STDProject.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StudentController {
	
	@Autowired
	private  StudentService service;
	
	
	@RequestMapping("/")
	public String home() {
		
		return "redirect:/main";
//		return "redirect:/health";
	}
	
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
