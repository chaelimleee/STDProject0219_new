package com.javateam.STDProject.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javateam.STDProject.domain.Users;

import lombok.extern.slf4j.Slf4j;
/*
 * Spring JDBC 사용 : javateacher
 */
@Repository("authJdbcService") //이 이름을 통해 다른 컴포넌트에서 이 빈을 주입할 때 사용
@Slf4j
public class AuthJdbcService {
	
	private JdbcTemplate jdbcTemplate;//JDBC(Java Database Connectivity)를 사용하여 데이터베이스와 상호 작용

    @Autowired
    public void setDataSource(DataSource dataSource) {
    	//JdbcTemplate을 초기화합니다. 이를 통해 데이터베이스에 대한 쿼리를 실행
		this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    //로그인과 회원가입 시 사용자 아이디가 이미 존재하는지 확인. 
    public boolean hasUsername(String username) { // 사용자의 이름이 데이터베이스에 존재하는지 여부 확인. 
    	// 존재여부는 boolean을 사용해서 true false로 나타내는 것이 적절. 
    	boolean flag = false; // 조건 표현.
    	String sql = "SELECT * FROM users WHERE username=?";
    	
    	log.info("hasUsername : " + username);
    	
    	try {
    		//this.jdbcTemplate.queryForObject: JDBC 템플릿을 사용하여 쿼리를 실행하고 그 결과를 객체로 변환
    		 Users user = (Users)this.jdbcTemplate
    				 				 .queryForObject(sql, //queryForObject: 단일 결과를 반환하는 쿼리를 실행/ 결과를 지정한 타입의 객체로 변환
								   				     new BeanPropertyRowMapper<Users>(Users.class),//결과를 Users 클래스의 객체로 매핑하는 데 사용되는 RowMapper
								   				     new Object[]{ username });//SQL 쿼리에 전달할 매개변수
    		 //BeanPropertyRowMapper: 각 결과 행의 컬럼을 객체의 프로퍼티에 자동으로 매핑하는 데 사용되는 행 매퍼입니다.
    		 //Users.class: 매핑할 객체의 클래스입니다.
    		 flag = user != null ? true : false;
    		 
		} catch (Exception e) {
			flag=false;
		}
    	
    	return flag; // 
    } //
    
    
    public void insertUsers(Users users, String role) { // 회원가입 발생 시 사용자 정보 데이터베이스에 저장. 
    	
    	log.info("insertUsers");
    	
    	String sql  = "INSERT INTO users VALUES (?,?,1)";
    	String sql2 = "INSERT INTO user_roles VALUES "
    				+ "(user_roles_seq.nextval,?,?)";
    	
    	this.jdbcTemplate.update(sql, 
    							 new Object[] { users.getUsername(), 
											    users.getPassword() });
    	
    	this.jdbcTemplate.update(sql2, 
    							 new Object[] { users.getUsername(),
											    role });
    	
    } //

} //