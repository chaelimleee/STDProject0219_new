package com.javateam.jdbc.test;

import com.javateam.jdbc.dao.UserDAO;

public class SelectTest {

	public static void main(String[] args) {

		UserDAO dao = new UserDAO();
		System.out.println(dao.selectUser("abcd"));
	}

}
