package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.poseidon.db.DBConnection;

public class BoardDAO {
	DBConnection dbCon = new DBConnection();
	
	
	
	// 글 저장하기 2024.01.02
	public int write(String title, String content) {
		int result = 0;
		
		Connection con = dbCon.getConn();
		PreparedStatement pstmt = null;
		//ResultSet은 필요없다
		String sql = "INSERT INTO board (board_title, board_content, board_write) VALUES (?, ?, ?)";
		String name = "포세이돈"; // 나중에 세션에서 받아오기
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, name);
			
			// insert문장, update문장 등에서 영향받은 레코드 숫자를 반환
			result = pstmt.executeUpdate(); //결과를 숫자로 되돌려줌. = 변경된 레코드 수
			/*
			 * execute();		실행, 참거짓	= update, insert, delete문
			 * executeQuery();	실행, rs		= select문
			 * executeUpdate();	실행, 숫자		= update, insert, delete문
			 * 
			 */
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		return result;
	}
	
	// 글 삭제하기
	public void delete(String no) {
		Connection conn = dbCon.getConn();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM board WHERE board_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.execute();	//executeQuery -> 뭔가 리턴하는 게 있는 애들. (이건 rs 필요)
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}
	
	// 톺아보기
	public Map<String, Object> detail(String no) {
		Map<String, Object> detail = new HashMap<String, Object>();
		Connection conn = dbCon.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board WHERE board_no = ?";
		
		//조회수 + 1하기 메소드를 호출합니다.
		countUp(no);
		
		try {
			pstmt = conn.prepareStatement(sql);
			//상기 sql문의 ?를 처리하기 위해 로직 추가
			pstmt.setString(1, no); //첫번째 물음표에 no라고 넣어준다. 0아니고 1. sql이라서
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				detail.put("board_no", rs.getInt("board_no"));
				detail.put("board_title", rs.getString("board_title"));
				detail.put("board_content", rs.getString("board_content"));
				detail.put("board_write", rs.getString("board_write"));
				detail.put("board_date", rs.getString("board_date"));
				detail.put("board_count", rs.getInt("board_count"));
			}
			
		} catch (SQLException e) {
//			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn); // 메소드 만들어서 씀.
		}
		
		
		return detail;
	}
	
	// 읽음 수 올리기
	private void countUp(String no) {
		Connection conn = dbCon.getConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_count = board_count + 1 WHERE board_no = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}

	// 게시판 글 보기
	public List<Map<String, Object>> boardList() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = dbCon.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null; // 결과가 나오니깐 rs
		String sql = "SELECT * FROM boardview"; // view 만들어서 쿼리문 수납. 회사마다 다르긴하다(복잡해서 공부해야됨)
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> e = new HashMap<String, Object>();
				//내부 객체
				e.put("board_no", rs.getInt("board_no"));
				e.put("board_title", rs.getString("board_title"));
				e.put("board_write", rs.getString("board_write"));
				e.put("board_date", rs.getString("board_date"));
				e.put("board_count", rs.getInt("board_count"));
				list.add(e);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		
		return list;
	}
	
	private void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		// 얘네 너무 길어서 옆으로 쭉 쓰기도 한다.
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 글 수정하기
	public void update(String no, String title, String content) {
		Connection con = dbCon.getConn();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_title=?, board_content=? WHERE board_no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, no);
			
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
	}
}
