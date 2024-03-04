package com.javateam.jdbc.test;

import com.javateam.jdbc.dao.UserDAO;
import com.javateam.jdbc.domain.User;

public class InsertTest {

	public static void main(String[] args) {

		UserDAO dao = new UserDAO();
		User user = new User();
		user.setUserId("abcd123");
		user.setUserName("자바맨");
		user.setUserPassword("1234");
		user.setUserAge(20);
		user.setUserEmail("abcd@abcd.com");
		
		dao.insertUser(user);
	}

	
}
