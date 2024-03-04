package com.javateam.jdbc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javateam.jdbc.domain.User;
import com.javateam.jdbc.util.DbUtil;

public class UserDAO {
	
	// DB 삽입
	public void insertUser(User user) {
		
		// DB 연결
		Connection con = DbUtil.connect();
		// SQL
		String sql = "insert into users values (?,?,?,?,?)";
		
		// SQL 처리객체
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserPassword());
			pstmt.setInt(4, user.getUserAge());
			pstmt.setString(5, user.getUserEmail());
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
			}
			
		} catch (SQLException e) {
			System.err.println("SQL 에러 : ");
			e.printStackTrace();
		}
		
	}
	
	public void updateUser(User user) {
		
		Connection con = DbUtil.connect();
		
		String sql = "update users set "
				   + "userpassword=?, "
				   + "useremail=? "
				   + "where userid=? " ;
		
		// SQL 처리객체
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, user.getUserPassword());
			pstmt.setString(2, user.getUserEmail());
			pstmt.setString(3, user.getUserId());
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
			
		} catch (SQLException e) {
			System.err.println("SQL 에러 : ");
			e.printStackTrace();
		}
	}
		
	public void makeRandomUsers() {
		
		Connection con = DbUtil.connect();
		 
		String plsql = "{ call random_users_generator() }";
		
		// SQL 처리객체
		try (CallableStatement pstmt = con.prepareCall(plsql)) {
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("생성 성공");
			} else {
				System.out.println("생성 실패");
			}
			
		} catch (SQLException e) {
			System.err.println("SQL 에러 : ");
			e.printStackTrace();
		}
		
	} //

	public void makeRandomNames() {
		
		Connection con = DbUtil.connect();
		 
		String plsql = "{ call random_name_generator() }";
		
		// SQL 처리객체
		try (CallableStatement pstmt = con.prepareCall(plsql)) {
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
			
		} catch (SQLException e) {
			System.err.println("SQL 에러 : ");
			e.printStackTrace();
		}
		
	}
	
	public User selectUser(String userid) {
		
		User user = null;
		
		Connection con = DbUtil.connect();
		
		String sql = "select * from users "
				   + "where userid='" + userid + "'";
		
		try (PreparedStatement pstmt = con.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();) {
			
			// pstmt.setString(1, userid);
			if (rs.next()) {
				
				System.out.println("개별 회원 정보 조회 성공");
				
				user = new User();
				// user.setUserId(rs.getString(1));
				user.setUserId(rs.getString("userid"));
				user.setUserName(rs.getString("username"));
				user.setUserPassword(rs.getString("userpassword"));
				user.setUserAge(rs.getInt("userage"));
				user.setUserEmail(rs.getString("useremail"));
				
			} else {
				System.out.println("개별 회원 정보 조회 실패");
			}
			
			
		} catch (SQLException e) {
			System.err.println("SQL 에러 : ");
			e.printStackTrace();
		}
		
		return user;
	}
	
	// 모든 데이터 가져오기
	public List<User> selectAllUsers() {
		
		List<User> users = new ArrayList<>();
		User user = null; // 여기서는 초기화만 시켜놓고 생성은 안에서
		
		Connection con = DbUtil.connect();
		
		String sql = "select * from users ";
		
		try (PreparedStatement pstmt = con.prepareStatement(sql);
			 ResultSet rs = pstmt.executeQuery();) {
			
			// pstmt.setString(1, userid);
			while (rs.next()) {
				
				user = new User(); // 생성은 안에서 해서 비우고 다시 넣고 비우고 다시 넣고,,
				// user.setUserId(rs.getString(1));
				user.setUserId(rs.getString("userid"));
				user.setUserName(rs.getString("username"));
				user.setUserPassword(rs.getString("userpassword"));
				user.setUserAge(rs.getInt("userage"));
				user.setUserEmail(rs.getString("useremail"));
				
				users.add(user);
			}
				
		} catch (SQLException e) {
			System.err.println("SQL 에러 : ");
			e.printStackTrace();
		}
		
		return users;
	}
	
	// 페이징 처리
	public List<User> selectUsersByPaging(int page, int limit) {
		
		List<User> users = new ArrayList<>();
		User user = null; // 여기서는 초기화만 시켜놓고 생성은 안에서
		
		Connection con = DbUtil.connect();
		
		String sql = "SELECT *  "
					+ "FROM (SELECT m.*,  "
					+ "             FLOOR((ROWNUM - 1) /" + limit + " + 1) page  "
					+ "      FROM ("
					+ "             SELECT *"
					+ "			    FROM users"
					+ "             ORDER BY userid ASC"
					+ "           ) m  "
					+ "      )  "
					+ "WHERE page = " + page
					+ ""
					+ "";
		
		try (PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			
			// pstmt.setString(1, userid);
			while (rs.next()) {
				
				user = new User(); // 생성은 안에서 해서 비우고 다시 넣고 비우고 다시 넣고,,
				// user.setUserId(rs.getString(1));
				user.setUserId(rs.getString("userid"));
				user.setUserName(rs.getString("username"));
				user.setUserPassword(rs.getString("userpassword"));
				user.setUserAge(rs.getInt("userage"));
				user.setUserEmail(rs.getString("useremail"));
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			System.err.println("SQL 에러 : ");
			e.printStackTrace();
		}
		
		return users;
	}
	
	public void deleteUser(String userid) {
			
			Connection con = DbUtil.connect();
						 
			String sql = "delete users where userid=?";
			
			// SQL 처리객체
			try (PreparedStatement pstmt = con.prepareStatement(sql)) {
				
				pstmt.setString(1, userid);
				
				if (pstmt.executeUpdate() == 1) {
					System.out.println("삭제 성공");
				} else {
					System.out.println("삭제 실패");
				}
				
			} catch (SQLException e) {
				System.err.println("SQL 에러 : ");
				e.printStackTrace();
			}
			
		} //
}
