package com.javateam.STDProject.domain;

import org.springframework.security.core.GrantedAuthority;

//권한 정보 관리 .
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 7464267597005842862L; // 직렬화 //클래스의 구조가 변경되었을 때 버전 충돌을 방지
	
	private String username;
	private String role; // 사용자 권한 .

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return this.role;
	}
   
}