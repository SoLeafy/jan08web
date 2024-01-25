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
	
	public List<Integer> notYet(){
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
		
		List<Integer> students = new ArrayList<Integer>();
		for(int i = 1; i < 28; i++) {
			students.add(i);
		}
		
		//students에 있는 사람 중... list에 없는 사람. .contains
		for(int i = 1; i < list.size(); i++) {
			if(students.contains(list.get(i))) {
				students.remove(list.get(i));
			}
		}
		
		return students;
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
	
	
}
