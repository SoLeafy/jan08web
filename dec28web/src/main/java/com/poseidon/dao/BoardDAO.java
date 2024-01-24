package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poseidon.db.DBConnection;
import com.poseidon.dto.BoardDTO;

public class BoardDAO {
	DBConnection dbCon = new DBConnection();
	public List<BoardDTO> boardList() { //selectBoard 아니고 이 이름도 쓴다
		List<BoardDTO> list = null;
		Connection conn = dbCon.getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM board";
		
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			list = new ArrayList<BoardDTO>();
			
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_write(rs.getString("board_write"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_count(rs.getInt("board_count"));
				list.add(dto);
			}
			
		} catch (SQLException e) {
//			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
//				e.printStackTrace();
			}
		}
		
		
		return list;
	}
}
