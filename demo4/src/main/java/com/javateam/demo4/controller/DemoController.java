package com.javateam.demo4.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javateam.demo4.domain.MemberVO;

@Controller
public class DemoController {
	
	//로그
	private static final Logger log = LoggerFactory.getLogger(DemoController.class);
	
	@GetMapping("/")
	public String home() {
		
		//redirect : 리다이랙트는 home메소드에서 처리하고 바로 viewMembers로 가게 함.  
		return "redirect:/viewMembers";
	}
	
	//아이디 받아서 viewMember.html로 보냄
	@GetMapping("/viewMember/{id}")
	public String viewMember(@PathVariable("id") String id, Model model) {
		
		log.info("viewMember");
		model.addAttribute("member", new MemberVO(id, "1234567", "홍길동", new Date(System.currentTimeMillis())));
		//System.currentTimeMillis() : 현재 시간 구하기.
		return "viewMember";
	}
	
	//아이디 받아서 modify.html 로 보냄
	@GetMapping("/modify/{id}")
	public String modify(@PathVariable("id") String id, Model model) {
		
		log.info("modify");
		log.info("#### id : "+id);
		
		model.addAttribute("member", 
				new MemberVO(id, "1234567", "홍길동", new Date(System.currentTimeMillis())));
		
		return "modify";
	}
	
	//viewMembers.html 로 보냄
	@GetMapping("/viewMembers")
	public String viewMembers(Model model) {
		
		log.info("viewMembers");
		//MemberVO의 모든 요소들를 members에 보냄. //ArrayList는 배열과 다르게 크기가 가변적으로 변함. 
		List<MemberVO> members = new ArrayList<>();
		
		// 비어있는 members에 add를 이용해 데이터를 넣어줌. 
		members.add(new MemberVO("springboot", "1234567", "홍길동", new Date(System.currentTimeMillis())));
		members.add(new MemberVO("springlegacy", "1234567", "장길산", new Date(System.currentTimeMillis())));
		members.add(new MemberVO("thymeleaf", "1234567", "임꺽정", new Date(System.currentTimeMillis())));
		
		//viewMembers의 members에 "members"를 보냄.
		model.addAttribute("members", members);
		
		//viewMembers의 result에 "성공"을 보냄.
		model.addAttribute("result", "성공");
		
		return "viewMembers";
	}
	
	@GetMapping("/demoUtilExp")
	public String demoUtilExp(Model model) {
		
		log.info("demoUtilExp");
		
		model.addAttribute("price", 123456789);
		model.addAttribute("sentence", "It is a Spring Boot");
		
		return "demoUtilExp";
	}
	
	@GetMapping("/templateDemo")
	public String templateDemo(Model model) {
		
		log.info("templateDemo");
		
		return "template";
	}

}