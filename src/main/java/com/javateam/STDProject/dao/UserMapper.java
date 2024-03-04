/**
 * 
 */
package com.javateam.STDProject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Role;

import com.javateam.STDProject.domain.Users;


/**
 * mapper
 * 
 * @author javateam
 *
 */
public interface UserMapper {
	
	Users getUserByUsername(String userName);
	List<Role> getUserRolesByUsername(String userName);
	
	int hasUsername(String username);
	
	// Users loadUserByUsername(String userName);
	void insertUser(@Param("users") Users users);
	void insertUserRoles(@Param("username") String username, 
						 @Param("role") String role);

}