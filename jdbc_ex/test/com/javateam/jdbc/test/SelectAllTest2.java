package com.javateam.jdbc.test;

import com.javateam.jdbc.dao.UserDAO;
import com.javateam.jdbc.domain.User;

public class SelectAllTest2 {

	public static void main(String[] args) {

		UserDAO dao = new UserDAO();
		for(User u : dao.selectAllUsers()) {
			System.out.println(u);
		}
	}

}
