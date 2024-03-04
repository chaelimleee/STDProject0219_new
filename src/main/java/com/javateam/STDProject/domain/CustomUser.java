package com.javateam.STDProject.domain;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor // 기본 생성자 자동 생성. 
public class CustomUser implements UserDetails { // 스프링시큐리티에 사용자 정보를 나타냄. 
	// 직렬화는 객체를 바이트 스트림으로 변환하여 저장하거나 전송하는 프로세스
	// 이때, 객체의 클래스 버전이 변경되면 이전에 직렬화된 객체를 역직렬화할 때 문제가 발생할 수 있음. 이런 문제 방지.
	// 1L은 직렬 버전 UID의 값을 나타냄.  만약 클래스의 구조가 변경되면 이 값을 증가시켜줘야함. 이를 통해 직렬화된 객체와 클래스의 버전이 일치하는지 확인가능.
	private static final long serialVersionUID = 1L; 
	
	private String username; //사용자의 식별자로 사용되는 사용자 이름
    private String password; //사용자의 비밀번호.
 
    /* Spring Security related fields */
    private List<Role> authorities; // 사용자가 가지고 있는 권한 목록
    private boolean accountNonExpired = true; // 사용자 계정이 만료 여부. true시 만료되지 않음.
    private boolean accountNonLocked = true; // 사용자 계정이 잠금 여부. true시 잠겨있지 않음.
    private boolean credentialsNonExpired = true; // 사용자 자격 증명 만료 여부. true시 만료되지 않음. 
    private boolean enabled = true;// 사용자 계정 활성화 여부 true시 활성화되있음. 
	
    public CustomUser(Users users) { // users객체를 받아와서 해당 객체에서 사용자 정보를 추출해서 customuser객체 생성.
		this.username = users.getUsername();
		this.password = users.getPassword();
		this.enabled = users.getEnabled()==1 ?  true : false; // 사용자 활성화 여부 가져옴. 1이면 true. 아니면 false.
	}

    // 추가
 	public CustomUser(String username, String password, boolean enabled) { // 사용자의 이름. 비밀번호. 활성화 여부를 받아옴. 
 		this.username = username;
 		this.password = password;
 		this.enabled = enabled;
 	}
    
}