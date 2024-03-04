package com.javateam.jdbc.test;

import com.javateam.jdbc.dao.UserDAO;
import com.javateam.jdbc.domain.User;

public class SelectPagingTest3 {

	public static void main(String[] args) {

		UserDAO dao = new UserDAO();
		for(User u : dao.selectUsersByPaging(1, 10)) {
			System.out.println(u);
		}
	}

}
