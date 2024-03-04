package com.javateam.demo6.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
	
	private static final Logger log
		= LoggerFactory.getLogger(DemoController.class);
	
	@GetMapping("/demo")
	@ResponseBody
	public String demo(@RequestParam String p) {
		
		log.info("demo");
		
		log.info("p = " + p );
		
		return p;
	}
}
