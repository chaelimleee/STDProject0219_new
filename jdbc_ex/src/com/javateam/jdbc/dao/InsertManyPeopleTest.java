package com.javateam.jdbc.dao;

public class InsertManyPeopleTest {

	public static void main(String[] args) {
		// 페이징 적용을 위해서 100명 분량의 임의 회원 데이터를 생성
		UserDAO dao = new UserDAO();
		dao.makeRandomUsers();
		dao.makeRandomNames();
	}

}
