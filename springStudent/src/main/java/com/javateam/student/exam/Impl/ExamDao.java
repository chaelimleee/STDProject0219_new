package com.javateam.student.exam.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javateam.student.jdbc.JDBCUtil;


@Repository
public class ExamDao implements Exam{
	
	JDBCUtil jdbc=null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ExamDao () {
		jdbc = JDBCUtil.getInstance();
	}

	@Override
	public void insert(ExamVO vo) {
		String SQL = "";
		try {
			conn = jdbc.getConnection();
			SQL = "insert into examtbl values('?','?',?,?,?,?)";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getsNo());
			pstmt.setString(2, vo.getsName());
			pstmt.setInt(3, vo.getKor());
			pstmt.setInt(4, vo.getEng());
			pstmt.setInt(5, vo.getMath());
			pstmt.setInt(6, vo.getHist());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(pstmt, conn);
		}
		
		
	}

	@Override
	public void update(ExamVO vo) {
//		String SQL = "";
//		try {
//			conn = jdbc.getConnection();
//			SQL = "update examtbl set sno = ? , sname =? , kor = ? , eng = ? , math = ?, hist =? ";
//			pstmt = conn.prepareStatement(SQL);
//			pstmt.setString(1, vo.getsNo());
//			pstmt.setString(2, vo.getsName());
//			pstmt.setInt(3, vo.getKor());
//			pstmt.setInt(4, vo.getEng());
//			pstmt.setInt(5, vo.getMath());
//			pstmt.setInt(6, vo.getHist());
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			jdbc.close(pstmt, conn);
//		}
		
	}

	@Override
	public int delete(int sno) {
		return 0;
	}

	@Override
	public ExamVO getExam(int sno) {
		return null;
	}

	@Override
	public List<ExamVO> getExamList(ExamVO vo) {
		List<ExamVO> list = new ArrayList<ExamVO>();
		String SQL = "";
		try {
			conn = jdbc.getConnection();
			SQL = "select * from examtbl order by sno asc ";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ExamVO examV = new ExamVO();
				examV.setsNo(rs.getString("sno"));
				examV.setsName(rs.getString("sName"));
				examV.setKor(rs.getInt("kor"));
				examV.setEng(rs.getInt("eng"));
				examV.setMath(rs.getInt("math"));
				examV.setHist(rs.getInt("hist"));
				list.add(examV);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(rs, pstmt, conn);
		}
		
		
		return list;
	}

	@Override
	public int summ() {
		String SQL = "";
		int sum = 0 ;
		try {
			conn = jdbc.getConnection();
			SQL = "select kor+eng+math+hist as summ from examtbl order by sno asc ";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				sum = rs.getInt("summ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(pstmt, conn);
		}
		return sum;
	}
	

}
