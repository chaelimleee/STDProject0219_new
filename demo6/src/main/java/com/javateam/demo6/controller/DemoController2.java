package com.javateam.demo6.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DemoController2 {
	
	private static final Logger log
		= LoggerFactory.getLogger(DemoController2.class);
	
	@GetMapping("/demo2")
	@ResponseBody
	public String demo(@RequestParam Map<String,String> map,
						HttpServletRequest request) {
		
		log.info("demo2");
		
		String msg = "";
		
		map.forEach((k,v) -> {
			log.info(k + "=" + v);
		});
		
		
		return "인자";
	}
}
