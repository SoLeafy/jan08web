package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poseidon.db.DBConnection;
import com.poseidon.dto.EmployeesDTO;

public class EmployeesDAO {
	DBConnection dbCon = new DBConnection();
	
	public List<EmployeesDTO> selectEmployees() {
		List<EmployeesDTO> result = new ArrayList<EmployeesDTO>();
		Connection conn = dbCon.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT *FROM employees LIMIT 0, 10"; //LIMIT은 반드시 거는 것이다~
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			result = new ArrayList<EmployeesDTO>();
			
			while (rs.next()) {
				EmployeesDTO em = new EmployeesDTO();
				em.setEmp_no(rs.getInt(1));
				em.setBirth_date(rs.getString(2));
				em.setFirst_name(rs.getString(3));
				em.setLast_name(rs.getString(4));
				em.setGender(rs.getString(5));
				em.setHire_date(rs.getString(6));
				result.add(em);
			}
			
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		
		
		return result;
	}
}
