package com.javateam.STDProject.controller;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;

import com.javateam.STDProject.domain.CustomUser;
import com.javateam.STDProject.domain.Users;
import com.javateam.STDProject.service.AuthMyBatisService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
 
@Controller
@Slf4j
public class AuthController {
	
	@Autowired //Spring 빈의 의존성을 자동으로 주입
	private AuthMyBatisService authMyBatisService;//사용자 인증 및 권한 부여와 관련된 비즈니스 로직을 처리//pring Security와 연동하여 사용자의 로그인 및 권한 부여를 담당하고, 데이터베이스와의 상호 작용을 처리
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;  // 패스워드 인코딩 클래스 //사용자의 비밀번호 안전하게 해싱(암호화)
	
//	@RequestMapping("/")
//	public String homeAuth() {
//		
//		log.info("homeAuth");
//		
//		return "redirect:/login";
//	}
	
	// 회원 가입폼
	@RequestMapping("/join")
	public String join() {
		
		log.info("/log/join");
		
		return "/log/join";
		
	}
	
	// 회원가입 처리
	@RequestMapping("/joinAction")
	public String join(@RequestParam("username") String username,
					   @RequestParam("password") String password,
					   Model model) {
		
		log.info("join Action");
		// 회원가입 성공 시 리다이렉트 .
		String path = "redirect:/login";
		
		String hashedPassword = ""; 
		// 패쓰워드 암호화
		hashedPassword = passwordEncoder.encode(password); 

		log.info("hashedPassword : "+hashedPassword);

		// 사용자 정보 생성 //회언가입시 필요한 사용자 정보 담은 users객체 생성. 
		Users users = new Users(username, hashedPassword, 1); 

		try {
			// 사용자 권한 할당 및 사용자 정보 저장 //"ROLE_USER" 역할을 부여
			authMyBatisService.insertUsers(users, "ROLE_USER"); 
				
		} catch (Exception e) {
			log.error("db 에러 발생 : " + e.getMessage());
			model.addAttribute("db_error", e.getMessage());
			// 모델에 "db_error" 속성을 추가하여 에러 페이지로 리다이렉트
			path = "/error/db-err";
		} //
		
		return path;
	} // 
	
	// myPage
	@GetMapping("/myPage")
	public String myPage() {
	
		log.info("myPage");
		
		return "/log/myPage";
	} //
    
    @GetMapping("/helloworld")
    public String helloWorld(ModelMap model) {
    	
    	log.info("hellowWolrd");
    	
	    model.addAttribute("message", "Welcome to the Hello World page");
	    
	    return "/log/helloworld";
    }
        
    // 관리자용 주소
    @GetMapping("/admin/home")
	public String securedAdminHome(ModelMap model) {
	
    	log.info("/admin/home");
    	
    	// Security pricipal 객체 (사용자 인증/인가 정보 객체)
		Object principal = SecurityContextHolder.getContext()
												.getAuthentication()
												.getPrincipal();
		
		CustomUser user = null;
		
		if (principal instanceof CustomUser) {
			user = ((CustomUser)principal);
		}
		
		log.info("user : " +user);
		
		String name = user.getUsername();
		model.addAttribute("username", name);
		model.addAttribute("message", "관리자 페이지에 들어오셨습니다.");
		
		return "/admin/home";
    }
    
    // (일반)사용자용 주소
    @GetMapping("/secured/home")
    public String securedHome(ModelMap model) {
    	
    	log.info("/secured/home");
    	
        Object principal = SecurityContextHolder.getContext()
        										.getAuthentication()
        										.getPrincipal();
        
        CustomUser user = null;
        
        if (principal instanceof CustomUser) {
        	user = ((CustomUser)principal);
        }
        
        log.info("user : "+user);
     
	    String name = user.getUsername();
	    model.addAttribute("username", name);
	    model.addAttribute("message", "일반 사용자 페이지에 들어오셨습니다.");
	    
	    return "/secured/home";
    }
    
    // 로그인 폼
    @GetMapping("/login")
    public String login(ModelMap model) {
    	
    	log.info("loginForm");
    	
    	return "/log/loginForm";
    }
    
    // 로그아웃 처리
    @GetMapping("/logoutProc")
    public String logout(ModelMap model,
    					 HttpServletRequest request,
    					 HttpServletResponse response) {
    	
    	log.info("logout");
    	
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    
	    log.info("auth : "+auth);
	    
	    // logout !
	    if (auth != null) {    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }

	    return "/log/logout";
    } //
    
    // 로그인(인증) 에러
    @GetMapping("/loginError")
    public String loginError(ModelMap model, HttpSession session) {
    	
		// Spring CustomProvider 인증(Authentication) 에러 메시지 처리
		Object secuSess = session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
	
		log.info("#### 인증 오류 메시지-1 : " + secuSess);
		log.info("#### 인증 오류 메시지-2 : " + secuSess.toString());
	
		model.addAttribute("error", "true");
		model.addAttribute("msg", secuSess);
	
		return "/log/loginForm";
    } //
    
    
    // id 존재 여부 점검
 	@RequestMapping("/login/idCheck")
 	@ResponseBody
 	public String idCheck(@RequestParam("username") String username,
 						  Model model) {
 		
 		log.info("username : ", username);
 		
 		boolean flag = authMyBatisService.hasUsername(username);
 		log.info("flag : ", flag);
 		
 		return flag + "";
 	} //
 	
 	
 	// access denied(403) : 인가(Authorization) 에러 : 접근 권한 부족 
 	@GetMapping("/403")
 	public String error403() {
 		
 		return "/error/403";
 	}
} //