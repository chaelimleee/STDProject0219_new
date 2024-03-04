package com.javateam.student.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import com.javateam.student.domain.BoardVO;
import com.javateam.student.jdbc.JDBCUtil;


public class BoardDaoImpl implements BoardDAO{
	
	JDBCUtil jdbc = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public BoardDaoImpl () {
		System.out.println("====> BoardDAO 생성");
		jdbc = JDBCUtil.getInstance();
	}
	

	@Override
	public void insert(BoardVO vo) {
		try {
			conn = jdbc.getConnection();
			String SQL = "insert into board(seq, title, writer, content) values((select nvl(max(seq),0)+1 from board ),?,?,?)";
			pstmt=conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(pstmt, conn);
		}
	}

	@Override
	public void update(BoardVO vo) {
		try {
			conn = jdbc.getConnection();
			String SQL = "update Board set title=? , writer=?, content=? where seq=?";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getSeq());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(pstmt, conn);
		}
	}

	@Override
	public void delete(int seq) {
		System.out.println("dao1 : " + seq);
		try {
			conn = jdbc.getConnection();
			String SQL = " delete from Board where seq = ? ";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			System.out.println("dao");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			jdbc.close(pstmt, conn);
		}
	}

	@Override
	public BoardVO getBoard(int seq) {
		BoardVO board = null;
		try {
			conn = jdbc.getConnection();
			String SQL = " select * from Board where seq = ? ";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(rs,pstmt, conn);
		}
		return board;
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		List<BoardVO> li = new ArrayList<BoardVO> ();
		try {
			conn = jdbc.getConnection();
			String SQL = " select seq, title, writer, regdate ,content from board order by  seq  desc ";
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regdate"));
				
				li.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdbc.close(rs,pstmt, conn);
		}
		return li;
	}


	//조회수 올라가게 함. 
	@Override
	public void cnt(int seq) {
		try {
			conn = jdbc.getConnection();
			String SQL= " update board set cnt=cnt+1 where seq = ? ";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, seq);
			pstmt.executeLargeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(pstmt, conn);
		}
		
		
	}

	//총 조회수
	@Override
	public int totalCount() {
		int tc = 0 ;
		try {
			conn = jdbc.getConnection();
			String SQL = "select count(*) tc from board"; // tc = totalCount! as로 이름 준거임.
			pstmt = conn.prepareStatement(SQL);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				tc=rs.getInt("tc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(rs, pstmt, conn);
		}
		return tc;
	}
	
	// 총 페이지
	@Override
	public int totalCount2() {
		BoardVO vo ;
		int tc = 0;
		
		try {
			vo = new BoardVO();
			conn = jdbc.getConnection();
			if (vo.getCh1() == null ) {
				String SQL = "select  count(*) tc  from board "; // tc = totalCount as로 이름 준 것. 
				pstmt=conn.prepareStatement(SQL);	
			}else if(vo.getCh1().equals("title")) {
				String SQL = "select  count(*) tc  from board where title like ? ";
				pstmt=conn.prepareStatement(SQL);
				pstmt.setString(1, "%"+vo.getCh2Text()+"%");
			}else if(vo.getCh1().equals("writer")) {
				String SQL = "select  count(*) tc  from board where writer like ?  ";
				pstmt=conn.prepareStatement(SQL);	
				pstmt.setString(1, "%"+vo.getCh2Text()+"%");
			}
			rs=pstmt.executeQuery();		
			if(rs.next()) {				
				tc=rs.getInt("tc");				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(rs , pstmt, conn);
		} 
		return tc;
	}


	//페이지 
	@Override
	public List<BoardVO> getBoardList2(BoardVO vo) {

		String SQL ="";		
		String SQL2 ="";		
		
		List<BoardVO> li=new ArrayList<BoardVO>();
		try {
			conn = jdbc.getConnection();
			
			SQL = " select  rownum, Q.* from ( select rownum as rnum, K.*  from  " ;
			SQL2= " where  rownum <= ?  )Q  where rnum >= ?  " ;  
			
			if (vo.getCh1() == null  || vo.getCh1().equals("null") && vo.getCh2Text().equals("null") ) {
				
		         SQL = SQL + " ( select seq,title,writer,content,cnt,regdate  from board order  by  seq  asc ) K   " ; 
		         SQL = SQL + SQL2 ;
		         
				pstmt=conn.prepareStatement(SQL);				
				pstmt.setInt(1, vo.getStart() + vo.getPageSize() - 1); // 한페이지에 몇개르 ㄹ보여줄 건지 10개 보여줌 
				pstmt.setInt(2, vo.getStart()); // 해당 페이지의 처음 rownum/ 1~10 이면 1부터, / 11~20이면 11부터. 
				
			} else if (vo.getCh1().equals("title")) {
			  	
		         SQL = SQL + " ( select seq,title,writer,content,cnt,regdate  from board  where title like ?  order  by  seq  asc ) K   " ; 
		         SQL = SQL + SQL2 ;
		         
				pstmt=conn.prepareStatement(SQL);	
				pstmt.setString(1, "%" + vo.getCh2Text() + "%");
				pstmt.setInt(2, vo.getStart() + vo.getPageSize() - 1);
				pstmt.setInt(3, vo.getStart());
				
			} else if (vo.getCh1().equals("writer")) {
		         SQL = SQL + " ( select seq,title,writer,content,cnt,regdate  from board  where writer like ?  order  by  seq  asc ) K   " ; 
		         SQL = SQL + SQL2 ;

				pstmt=conn.prepareStatement(SQL);	
				pstmt.setString(1, "%" + vo.getCh2Text() + "%");
				pstmt.setInt(2, vo.getStart() + vo.getPageSize() - 1);
				pstmt.setInt(3, vo.getStart());
				
			} else {
				SQL = SQL + " ( select seq,title,writer,content,cnt,regdate  from board where title like ? or writer like ? order  by  seq  asc ) K   " ; 
				SQL = SQL + SQL2 ;
				
				pstmt=conn.prepareStatement(SQL);				
				pstmt.setString(1, "%" + vo.getCh2Text() + "%");
				pstmt.setString(2, "%" + vo.getCh2Text() + "%");
				pstmt.setInt(3, vo.getStart() + vo.getPageSize() - 1); // 한페이지에 몇개르 ㄹ보여줄 건지 10개 보여줌 
				pstmt.setInt(4, vo.getStart()); // 해당 페이지의 처음 rownum/ 1~10 이면 1부터, / 11~20이면 11부터. 
				
			}
						  
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardVO	board=new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
				li.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.close(rs , pstmt, conn);
		} 
		return li;
		
	}



	   
	

}
