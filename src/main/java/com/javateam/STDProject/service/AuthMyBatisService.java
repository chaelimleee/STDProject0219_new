/**
 * 
 */
package com.javateam.STDProject.service;

import com.javateam.STDProject.domain.Users;

/**
 * @author javateam
 *
 */
public interface AuthMyBatisService {
	
	boolean hasUsername(String username);
	void insertUsers(Users users, String role);

} //