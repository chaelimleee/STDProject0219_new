/**
 * 
 */
package com.javateam.STDProject.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author javateam
 *
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.PasswordManagementConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.javateam.STDProject.service.CustomProvider;

import lombok.extern.slf4j.Slf4j;

// spring & thymeleaf : 
// https://www.thymeleaf.org/doc/articles/springsecurity.html

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig {

	@Autowired
	CustomProvider customProvider;
	
//	@Autowired
//  private final AuthenticationEntryPoint entryPoint;	// 추가

//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return (AuthenticationManager) this.customProvider;
//	}

	private UserDetailsService userDetailsService;

	private DataSource dataSource;

//	public WebSecurityConfig(UserDetailsService userDetailsService, DataSource dataSource, AuthenticationEntryPoint entryPoint) {
	public WebSecurityConfig(UserDetailsService userDetailsService, DataSource dataSource) {

		log.info("생성자 주입 wiring");
		this.dataSource = dataSource;
		this.userDetailsService = userDetailsService;
		// this.entryPoint = entryPoint;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	// @Order(1)
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.userDetailsService(userDetailsService); // 사용자 인증에 필요한 정보를 가져옴. 
		
		http.authenticationProvider(customProvider); // 사용자 정의된 인증 로직 처리 . 사용자가 제공한 인증정보 처리.

//		http.userDetailsService(userDetailsService)   // 체이닝 가능. 
//			.authenticationProvider(customProvider);
		
		/////////////////////////////////////////////////////////////////////////////////////////////

		// 동일 출처 정책(Same-origin policy) 
		// https://developer.mozilla.org/ko/docs/Web/Security/Same-origin_policy
		// https://ko.wikipedia.org/wiki/%EB%8F%99%EC%9D%BC-%EC%B6%9C%EC%B2%98_%EC%A0%95%EC%B1%85
		// https://docs.spring.io/spring-security/reference/servlet/exploits/headers.html#servlet-headers-frame-options
		
		//같은 출처에서만 로드되도록 제한. 
		http
			.headers(headers -> headers // 스프링 시큐리티 headers 메소드를 사용해서 헤더설정 지정.
				.frameOptions(frameOptions -> frameOptions // 람다 표현식. frameoptions에 대한 설정 지정. / 같은 출처에서만 로드되도록 제한. 
					.sameOrigin()// 같은 출처에서만 iframe으로 해당 페이지 로드. 
				)
			);
		
		// 주의) 코드 파편화 심한 구간 
		// https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/builders/HttpSecurity.html#authorizeHttpRequests(org.springframework.security.config.Customizer)
		
		// http.authorizeRequests()
		// http.authorizeHttpRequests()
		
		http.authorizeHttpRequests((authorizeHttpRequests) ->  
									authorizeHttpRequests
									// 해당 url을 허용한다.
									.requestMatchers("/resources/**", "/loginError", "/join", "/joinAction", 
													 "/login/idCheck", "/login")
									.permitAll()
									// admin 폴더에 경우 admin(ROLE_ADMIN) 롤이 있는 사용자에게만 허용
									// .antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN")
									.requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
									// user 폴더에 경우 user(ROLE_USER) 롤이 있는 사용자에게만 허용
									.requestMatchers("/user/**").hasAnyAuthority("ROLE_USER")
																.anyRequest().authenticated());

		/////////////////////////////////////////////////////////////////////////////////////////////////
		//
		// csrf(Cross-Site Request Forgery) token
		// : https://developer.mozilla.org/ko/docs/Glossary/CSRF
		// : https://namu.wiki/w/CSRF
		// : https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#csrf-components
		
		// http.csrf(Customizer.withDefaults());
		// http.csrf((csrf)->csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository()));
		http.csrf((csrf)->csrf.disable()); // csrf 토큰 미사용
		
		// - login : https://docs.spring.io/spring-security/reference/migration-7/configuration.html#_use_the_lambda_ds
		// - logout : https://docs.spring.io/spring-security/reference/servlet/authentication/logout.html#logout-java-configuration

		// 로그인/로그아웃 (인증) 처리
		http
			.formLogin(formLogin -> formLogin
						.loginPage("/login") // 로그인 이후 주소
							.usernameParameter("username") // 아이디
							.passwordParameter("password") // 패쓰워드
							.defaultSuccessUrl("/myPage") // 로그인 성공시 이동 주소
						.failureUrl("/loginError") // 로그인 에러 처리
						// .failureHandler(new CustomAuthenticationFailure()) // 로그인 실패 핸들러
						.permitAll())
			// logout 핸들링(처리)
			.logout((logout) -> logout
				        .logoutSuccessUrl("/myPage") // 로그아웃 이후 이동 주소
				        .permitAll()
				    );
		
		// 예외처시 해당 url로 이동
		http.exceptionHandling(handler -> handler.accessDeniedPage("/403")); 
		

		// 추가된 부분 : remember-me 관련
		// remember-me cookie
		// => 사용자이름 + cookie expired time(만료 시간) + 패쓰워드 => Base64(Md5방식) 암호화
		//
		// base64(username + ":" + expirationTime + ":" + md5Hex(username + ":" +
		//        expirationTime + ":" password + ":" + key))

		// howto-1)
		// https://docs.spring.io/spring-security/reference/servlet/authentication/rememberme.html#_persistenttokenbasedremembermeservices
		
		// 로그인 한 후에 브라우저를 닫았다가 열어도 로그인 상태를 유지해줌. 
		http.rememberMe((remember) -> remember
							.key("javateam") // 마음대ㅗㄹ,,
							.userDetailsService(userDetailsService) // 사용자 정보를 제공. 사용자 식별의 위해 사용. 
							.tokenRepository(getJDBCRepository()) // 토큰을 저장하는데에 사용되는 데이터베이스 저장소. 
							.tokenValiditySeconds(60 * 60 * 24)); // 24시간(1일)

		// howto-2) 토큰 기반 서비스
		// https://docs.spring.io/spring-security/reference/servlet/authentication/rememberme.html
//		http.rememberMe((remember) -> remember
//				.rememberMeServices(this.rememberMeServices(userDetailsService))
//			);

		return http.build();
	}
	
	// remember-me 관련 정보 DB 저장
	private PersistentTokenRepository getJDBCRepository() {

		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);

		return repo;
	} //
	
	// token 기반 remember-me 서비스
	@Bean
	RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
		
		RememberMeTokenAlgorithm encodingAlgorithm = RememberMeTokenAlgorithm.SHA256;
		TokenBasedRememberMeServices rememberMe 
			= new TokenBasedRememberMeServices("javateam", userDetailsService, encodingAlgorithm);
		rememberMe.setMatchingAlgorithm(RememberMeTokenAlgorithm.MD5);
		
		return rememberMe;
	}

}