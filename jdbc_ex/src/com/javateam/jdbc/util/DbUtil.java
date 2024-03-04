package com.javateam.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	
	public static Connection connect() {
		
		Connection con = null;

		// JDBC driver 등록
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			// Oracle 연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", 
											  "hr", "1234");

		} catch (ClassNotFoundException e) {

			System.out.println("JDBC 드라이버 등록 오류");
			e.printStackTrace();

		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			e.printStackTrace();
		}
		
		return con;

	} //
}
