package com.javateam.student.jdbc;

import java.sql.*;

public class JDBCUtil {
	
	Connection con = null; // Connection 이용해서 statement 객체 생성 
	PreparedStatement stmt = null; // sql문을 실행할 때 사용하는 인터페이스
	ResultSet rs = null; // Statement 객체의 결과 받기. 
	
	/*
	String jdbcUrl = "jdbc:mysql://localhost:3306/basicjsp";
	String dbId = "jspid";
	String dbPass = "jsppass";
	*/
	
	String url = "jdbc:oracle:thin:@//localhost:1521/xe";
	String userId = "hr";
	String pass = "1234";
	
	private static JDBCUtil db = new JDBCUtil();
	
	private JDBCUtil() {} // 기본생성자.
	
	public static JDBCUtil getInstance() {
		return db;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(url, userId, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void close(PreparedStatement stmt, Connection con) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(ResultSet rs, PreparedStatement stmt, Connection con) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
