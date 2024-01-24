package com.poseidon.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//데이터 접속 객체(Data Access Object)
//db에 붙어서 실질적으로 하는 것.
public class EmployeesDAO {
	DBConnection dbConn = new DBConnection(); //인스턴스 생성 -> 클래스 변수로,, 경찰은 총을 가지는 예시. 소유 관계 클래스?
	
	public List<EmployeesDTO> selectEmployees() {
		List<EmployeesDTO> result = null;
		//DB 접속정보
		Connection con = dbConn.getConnection();
		//각종 객체
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//sql
		String sql = "SELECT * FROM employees LIMIT 0, 10"; //LIMIT 안 걸면 되게 느려짐. 0부터 10개라는 뜻. 꼭 걸자.
		
		//로직
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); //select문은 옆 메소드로 실행함.
			
			result = new ArrayList<EmployeesDTO>();
			while (rs.next()) {
				EmployeesDTO dto = new EmployeesDTO();
				dto.setEmp_no(rs.getInt("emp_no"));
				dto.setBirth_date(rs.getString("birth_date"));
				dto.setFirst_name(rs.getString("first_name"));
				dto.setLast_name(rs.getString("last_name"));
				dto.setGender(rs.getString("gender").charAt(0));
				dto.setHire_date(rs.getString("hire_date"));
				
				result.add(dto);
			}
			
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public List<EmployeesDTO> selectDepartments() {
		List<EmployeesDTO> result = null;
		//데이터베이스 접속정보
		Connection conn = dbConn.getConnection(); //그 인스턴스에서 뽑아내는 것.
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//sql
		String sql = "SELECT * FROM departments";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			//데이터 만드는 작업
			result = new ArrayList<EmployeesDTO>(); //DTO 보단 key value가 편할 수 있지만 지금은 DTO
			//rs에 담긴 거 몇 개인지 몰라서 while
			while (rs.next()) {
				//DTO에 담는 모양. record 한 줄.
				EmployeesDTO dto = new EmployeesDTO(); //얘를 while문 밖에 뒀다면?
				dto.setDept_no(rs.getString(1));
				dto.setDept_name(rs.getString(2));
				result.add(dto);
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
