package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.poseidon.dto.CafeDTO;

public class CafeDAO extends AbstractDAO {

	public int saveMenu(CafeDTO dto) {
		int result = 0;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO cafe (studentNo, menu, menuComment) "
				+ "VALUES (?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getStudentNo());
			pstmt.setString(2, dto.getMenu());
			pstmt.setString(3, dto.getMenuComment());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		return result;
	}

	public int countMenu(String menu) {
		int result = 0;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT count(*) FROM cafeview WHERE menu=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menu);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Integer> studentsDone(){
		List<Integer> list = new ArrayList<Integer>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT studentNo FROM cafeview ORDER BY studentNo";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int e = rs.getInt(1);
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return list;
	}

	public List<Integer> whoseMenu(String menu) {
		List<Integer> list = new ArrayList<Integer>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT studentNo FROM cafeview WHERE menu=? ORDER BY studentNo";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menu);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int e = rs.getInt(1);
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return list;
	}
	
	public List<Map<Integer, String>> teaMenu(String menu) {
		List<Map<Integer, String>> list = new ArrayList<Map<Integer, String>>();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT studentNo, menuComment FROM cafeview WHERE menu=? ORDER BY studentNo";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menu);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Map<Integer, String> e = new HashMap<Integer, String>();
				e.put(rs.getInt("studentNo"), rs.getString("menuComment"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return list;
	}
	
	
}
