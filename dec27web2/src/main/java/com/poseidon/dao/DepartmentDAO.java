package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//데이터 접속 객체
import com.poseidon.db.DBConnection;
import com.poseidon.dto.DepartmentsDTO;

public class DepartmentDAO {
	DBConnection dbConn = new DBConnection();

	public List<DepartmentsDTO> selectDepartments() {
		List<DepartmentsDTO> list = null; // 여기서 안 만든 이유는 에러나지 않고 됐을 때에야 만들려고
		Connection con = dbConn.getConn();
		PreparedStatement pstmt = null; // sql injection 공격을 너무 당해서 막으려고 나옴.
		ResultSet rs = null;
		String sql = "SELECT * FROM departments";
		// 따로 선언해 준 이유 : try_catch 쓰면 지역 변수 돼버리니까 따로 해줌.
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); // 쿼리 실행한 게 rs에 담긴다.

			list = new ArrayList<DepartmentsDTO>();

			while (rs.next()) {
				DepartmentsDTO dto = new DepartmentsDTO();
				dto.setDept_no(rs.getString("dept_no"));// 컬럼명
				dto.setDept_name(rs.getString(2));// 컬럼 위치. 1부터 시작
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}

		return list;
	}

}
