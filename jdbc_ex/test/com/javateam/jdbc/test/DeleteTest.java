package com.javateam.jdbc.test;

import com.javateam.jdbc.dao.UserDAO;

public class DeleteTest {

	public static void main(String[] args) {

		UserDAO dao = new UserDAO();
		dao.deleteUser("abcd");
	} //
}
