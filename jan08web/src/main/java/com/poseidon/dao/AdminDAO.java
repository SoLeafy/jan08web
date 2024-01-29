package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poseidon.dto.BoardDTO;
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

}
