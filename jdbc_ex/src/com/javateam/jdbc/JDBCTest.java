package com.javateam.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest {

	public static void main(String[] args) {
		
		//JDBC driver 등록
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			//Oracle 연결 정보
			Connection conn =
			DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "1234");
			
			PreparedStatement pstmt =
					conn.prepareStatement("select count(*) from employees");
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int count = rs.getInt(1);
				System.out.println("count : " + count);
			}
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("JDBC 드라이버 등록 오류.");
			e.printStackTrace();
		} catch (SQLException e) {
			
			System.out.println("DB 연결 오류.");
			e.printStackTrace();
		}
		
	}

}
