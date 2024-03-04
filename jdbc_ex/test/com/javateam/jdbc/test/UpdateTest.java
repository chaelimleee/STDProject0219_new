package com.javateam.jdbc.test;

import com.javateam.jdbc.dao.UserDAO;
import com.javateam.jdbc.domain.User;

public class UpdateTest {

	public static void main(String[] args) {
		
		UserDAO dao = new UserDAO();
		User user = new User();
		
		user.setUserId("abcd");
		user.setUserPassword("123456");
		user.setUserEmail("abcd@abcd.com");
		
		dao.updateUser(user);
		

	}

}
