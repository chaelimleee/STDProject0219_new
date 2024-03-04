package com.javateam.STDProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javateam.STDProject.domain.StudentVO;
import com.javateam.STDProject.service.PagingJpaService;

import lombok.extern.slf4j.Slf4j;
 
@Controller
@Slf4j
public class PagingController {
   
    @Autowired
    private PagingJpaService svc;
    
    public @ResponseBody Page<StudentVO> getStrudnetListPaging(@PathVariable int page){
    	Page
		return null;
    	
    }
    
    @RequestMapping("/")
    public String home() {
    	
    	return "redirect:/paging?page=1"; // 페이징 html의 1페이지. 
    }
   
    @RequestMapping("/sort")
    public String sort(Model model) {
       
        log.info("sort");
       
        model.addAttribute("list", svc.findAll("오름차순")); // 모든 리스트 보여줌. 
       
        return "sorted";
    } //
   
   
    @RequestMapping("/paging")
    public String paging(@RequestParam("page") int page, Model model) {
       
        log.info("paging");
        
        Page<StudentVO> pageList = svc.findAllByPaging(page, 5); //해당 페이지에 항목 5개. 
        List<StudentVO> list = pageList.getContent();
       
        model.addAttribute("total_page", pageList.getTotalPages());
        model.addAttribute("curr_page", page);
        model.addAttribute("list", list);
       
        return "getboardList";
    } //
   
    @RequestMapping("/member/{id}")
    @ResponseBody
    public StudentVO getOne(@PathVariable int id) {
       
        log.info("getOne");
       
        return svc.findById(id);
    } //
 
}