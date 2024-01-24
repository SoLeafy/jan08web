package com.poseidon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poseidon.db.DBConnection;
import com.poseidon.dto.BoardDTO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

public class BoardDAO extends AbstractDAO {

	public List<BoardDTO> boardList(int page) {
		List<BoardDTO> list = null;
		// db정보
		// DBConnection db = DBConnection.getInstance();
		// conn 객체
		Connection con = db.getConnection();
		// pstmt
		PreparedStatement pstmt = null;
		// rs
		ResultSet rs = null;
		// sql
		String sql = "SELECT * FROM boardview LIMIT ?, 10";

		// con = db.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (page-1) * 10);
			rs = pstmt.executeQuery(); // executeQuery()는 rs에 담을 결과가 오는 것.
			list = new ArrayList<BoardDTO>(); // data가 있으면 만들어주기.

			while (rs.next()) {// 컬럼이름이나 번호나 하나로 통일해서 쓰는 것 권장.
				BoardDTO e = new BoardDTO();
				e.setNo(rs.getInt("board_no"));
				e.setTitle(rs.getString("board_title"));
				e.setWrite(rs.getString("board_write"));
				e.setDate(rs.getString("board_date"));
				e.setCount(rs.getInt("board_count"));
				e.setComment(rs.getInt("comment"));
				list.add(e);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}

		return list;
	}

	public BoardDTO detail(int no) {
		//countUp(no); //조회수 올리기
		
		BoardDTO dto = new BoardDTO();
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null; // SELECT문은 rs!!
		String sql = "SELECT b.board_no, b.board_title, b.board_content, m.mname AS board_write, m.mid, "
				+ "b.board_date, b.board_ip, "
				+ "(SELECT COUNT(*) FROM visitcount WHERE board_no=b.board_no) AS board_count "
				+ "FROM board b JOIN member m ON b.mno=m.mno "
				+ "WHERE b.board_no=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			// 글 하나니까 while아니고 if
			if(rs.next()) {
				dto.setNo(rs.getInt("board_no"));
				dto.setTitle(rs.getString("board_title"));
				dto.setContent(rs.getString("board_content"));
				dto.setWrite(rs.getString("board_write"));
				dto.setDate(rs.getString("board_date"));
				dto.setCount(rs.getInt("board_count"));
				dto.setMid(rs.getString("mid"));
				dto.setIp(rs.getString("board_ip"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return dto;
	}

	public int write(BoardDTO dto) {
		int result = 0;
		
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board (board_title, board_content, mno, board_ip) " //외래키 mno 넣고, 서브쿼리 날릴 것.
				+ "VALUES (?, ?, (SELECT mno FROM member WHERE mid=?), ?)"; //mid 갖다가 mno로 insert
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getMid());//수정완
			pstmt.setString(4, dto.getIp());//아이피 추가
			result = pstmt.executeUpdate();//영향받은 행을 result에 저장한다.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		
		return result;
	}

	public int delete(BoardDTO dto) {
		int result = 0;
		//conn
		Connection con = db.getConnection();
		//pstmt
		// try catch 안에서 만들면 그 안에서밖에 close할 수 없어서 밖에 만든다
		PreparedStatement pstmt = null; 
		//sql
		String sql = "UPDATE board SET board_del='0' WHERE board_no = ? AND mno=(SELECT mno FROM member WHERE mid=?)";
		
		//조립
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNo());
			pstmt.setString(2, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		return result;
	}

	public int update(BoardDTO dto) {
		int result = 0;
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE board SET board_title=?, board_content=? "
				+ "WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNo());
			pstmt.setString(4, dto.getMid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
		
		
		return result;
	}
	
// 로그인한 사람만 읽음 수 올리기
	public void countUp(int no, String mid) {
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //SELECT문이니까 rs가 존재.
		//이제 한 번 질의를 해서 물어보고 또 와야 함.
		String sql = "SELECT COUNT(*) FROM visitcount "
				+ "WHERE board_no=? AND mno=(SELECT mno FROM member WHERE mid=?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, mid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int result = rs.getInt(1);
				if(result == 0) { // 한 번도 저장된 적 없을 때 저장.
					realCountUp(no, mid);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
	}

	private void realCountUp(int no, String mid) {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO visitcount (board_no, mno) "
				+ "VALUES (?, (SELECT mno FROM member WHERE mid=?));";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, mid);
			pstmt.execute(); // 리턴값 걍 안받음.
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, con);
		}
	}

	public int totalCount() {
		Connection con = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM boardview";
		int result = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1); //컬럼 어차피 하나뿐이니까.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, con);
		}
		
		return result;
	}

	public List<CommentDTO> commentList(int no) {
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		Connection conn = db.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM commentview WHERE board_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setCno(rs.getInt("cno"));
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setComment(rs.getString("ccomment"));
				dto.setCdate(rs.getString("cdate"));
				dto.setMno(rs.getInt("mno"));//이제 뷰를 만들어서 mname, mid를 가져오게 해야,,,
				dto.setMid(rs.getString("mid"));
				dto.setMname(rs.getString("mname"));
				dto.setClike(rs.getInt("clike"));
				dto.setIp(Util.ipMasking(rs.getString("cip")));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return list;
	}

	
}