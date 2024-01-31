package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.dto.MemberDTO;

public class AdminDAO extends AbstractDAO {
	
	public List<MemberDTO> memberList() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT mno, mid, mname, mdate, mgrade FROM member";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO e = new MemberDTO();
				e.setMno(rs.getInt("mno"));
				e.setMid(rs.getString("mid"));
				e.setMname(rs.getString("mname"));
				e.setMdate(rs.getString("mdate"));
				e.setMgrade(rs.getInt("mgrade"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return list;
	}

	public List<MemberDTO> memberList(int grade) {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT mno, mid, mname, mdate, mgrade FROM member WHERE mgrade=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, grade);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO e = new MemberDTO();
				e.setMno(rs.getInt("mno"));
				e.setMid(rs.getString("mid"));
				//e.setMpw(rs.getString("mpw"));
				e.setMname(rs.getString("mname"));
				e.setMdate(rs.getString("mdate"));
				e.setMgrade(rs.getInt("mgrade"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return list;
	}

	public int updateMgrade(MemberDTO dto) {
		int result = 0;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE member SET mgrade=? WHERE mno=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getMgrade());
			pstmt.setInt(2, dto.getMno());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		return result;
	}

	public List<BoardDTO> boardList(int page) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT b.board_no, b.board_title, b.board_date, b.board_ip, b.board_del, "
				+ "(SELECT count(0) FROM visitcount v WHERE v.board_no = b.board_no) AS board_count, "
				+ "(SELECT count(0) FROM comment c WHERE c.board_no = b.board_no) AS comment, "
				+ "m.mname "
				+ "FROM board b JOIN member m ON (b.mno = m.mno) "
				+ "ORDER BY board_no DESC "
				+ "LIMIT ?, 10";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (page-1) * 10);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO e = new BoardDTO(); 
				e.setNo(rs.getInt("board_no"));
				e.setTitle(rs.getString("board_title"));
				e.setWrite(rs.getString("mname")); //join member
				e.setDate(rs.getString("board_date"));
				e.setCount(rs.getInt("board_count")); //join visitcount
				e.setComment(rs.getInt("comment")); //join comment
				e.setIp(rs.getString("board_ip"));
				e.setDel(rs.getInt("board_del")); //만들어주자.
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return list;
	}

	public int totalCount() {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM board";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return result;
	}

	public List<BoardDTO> boardList(String search) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT b.board_no, b.board_title, b.board_date, b.board_ip, b.board_del, "
				+ "(SELECT count(0) FROM visitcount v WHERE v.board_no = b.board_no) AS board_count, "
				+ "(SELECT count(0) FROM comment c WHERE c.board_no = b.board_no) AS comment, "
				+ "m.mname "
				+ "FROM board b JOIN member m ON (b.mno = m.mno) "
				+ "WHERE board_title LIKE CONCAT('%', ?, '%') "
				+ "OR board_content LIKE CONCAT('%', ?, '%') "
				+ "OR m.mname LIKE CONCAT('%', ?, '%') "
				+ "ORDER BY board_no DESC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setString(3, search);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("board_no"));
				dto.setTitle(rs.getString("board_title"));
				dto.setDate(rs.getString("board_date"));
				dto.setIp(rs.getString("board_ip"));
				dto.setDel(rs.getInt("board_del"));
				dto.setCount(rs.getInt("board_count"));
				dto.setComment(rs.getInt("comment"));
				dto.setWrite(rs.getString("mname"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return list;
	}

	public int boardDel(BoardDTO dto) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_del=? WHERE board_no=?";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(dto.getDel()));
			pstmt.setInt(2, dto.getNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		return result;
	}

	public List<CommentDTO> commentList() {
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT c.cno, c.board_no, SUBSTRING(REPLACE(c.ccomment, '<br>', ' '), 1, 15) AS ccomment, "
				+ "if(date_format(current_timestamp(),'%Y-%m-%d') = date_format(c.cdate,'%Y-%m-%d'), "
				+ "date_format(c.cdate,'%h:%i'),date_format(c.cdate,'%m-%d')) AS cdate, "
				+ "c.clike, c.mno, m.mid, m.mname, c.cip, c.cdel "
				+ "FROM (comment c JOIN member m ON(c.mno = m.mno)) "
				+ "ORDER BY c.cno DESC";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentDTO e = new CommentDTO();
				e.setCno(rs.getInt("cno"));
				e.setBoard_no(rs.getInt("board_no"));
				e.setComment(rs.getString("ccomment"));;
				e.setCdate(rs.getString("cdate"));
				e.setClike(rs.getInt("clike"));
				e.setMno(rs.getInt("mno"));
				e.setMname(rs.getString("mname"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		
		return list;
	}
	
	public List<Map<String, Object>> ipList() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT ino, iip, idate, iurl, idata FROM iplog ORDER BY ino DESC";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> e = new HashMap<String, Object>();
				e.put("ino", rs.getInt("ino"));
				e.put("iip", rs.getString("iip"));
				e.put("idate", rs.getString("idate"));
				e.put("iurl", rs.getString("iurl"));
				e.put("idata", rs.getString("idata"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return list;
	}

	public List<Map<String, Object>> ipList(String ip) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT ino, iip, idate, iurl, idata FROM iplog WHERE iip=? ORDER BY ino DESC";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ip);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> e = new HashMap<String, Object>();
				e.put("ino", rs.getInt("ino"));
				e.put("iip", rs.getString("iip"));
				e.put("idate", rs.getString("idate"));
				e.put("iurl", rs.getString("iurl"));
				e.put("idata", rs.getString("idata"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return list;
	}

	//가장 많이 접속한 ip
	public List<Map<String, Object>> topIpList() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT iip, COUNT(*) AS count FROM iplog GROUP BY iip ORDER BY COUNT(*) DESC LIMIT 0, 5";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ip", rs.getString("iip"));
				map.put("count", rs.getInt("count"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return list;
	}

	//최근 접속한 ip
	public List<Map<String, Object>> latestAccessIP10() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT iip,idate "
	            + "FROM (SELECT DISTINCT iip, idate FROM iplog ORDER BY idate DESC) AS t "
	            + "GROUP BY t.iip ORDER BY idate DESC; ";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ip", rs.getString("iip"));
				map.put("date", rs.getString("idate"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return list;
	}

	

}
