/**
 * 
 */
package com.javateam.STDProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Role;

import com.javateam.STDProject.domain.StudentVO;
import com.javateam.STDProject.domain.Users;


/**
 * mapper
 * 
 * @author javateam
 *
 */
public interface StudentMapper {
	public void insert(StudentVO vo);
	public void update(StudentVO vo);
	public void delete(int seq);
	
	public void cnt(int seq);
	public int totalCount();
	public int totalCount2();
	
	StudentVO getBoard(int seq);
	List<StudentVO> getBoardList(StudentVO vo) ;
	List<StudentVO> getBoardList2(StudentVO vo);
	List<StudentVO> getBoardList();
	
	
//	Users getUserByUsername(String userName);
//	List<Role> getUserRolesByUsername(String userName);
//	
//	int hasUsername(String username);
//	
//	// Users loadUserByUsername(String userName);
//	void insertUser(@Param("users") Users users);
//	void insertUserRoles(@Param("username") String username, 
//						 @Param("role") String role);

}