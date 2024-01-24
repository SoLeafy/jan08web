package com.poseidon.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dao.LogDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/board")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//삼항연산자로 해보기
		int page = request.getParameter("page") != null && request.getParameter("page") != "" 
				? Util.str2Int2(request.getParameter("page")) : 1;
//		if(request.getParameter("page") != null) {
//			page = Util.str2Int2(request.getParameter("page")); // url의 그 파라미터
//		}
		
		//log
		Map<String, Object> log = new HashMap<String, Object>();
		log.put("ip", Util.getIp(request));
		log.put("url", "./board");
		log.put("data", "page=" + page); //보드니까 게시판을 본 것.
		LogDAO logDAO = new LogDAO();
		logDAO.logWrite(log);
		
		//DAO랑 연결
		BoardDAO dao = new BoardDAO();
		List<BoardDTO> list = dao.boardList(page);
		int totalCount = dao.totalCount();
		
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("page", page);
		
		// 디스패쳐가 화면 내용만 jsp로 바꿔준다. (sendRedirect는 url도 바꾼다)
		RequestDispatcher rd = request.getRequestDispatcher("board.jsp");
		rd.forward(request, response); // url은 그대로, 화면 내용만 바꿈.
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
