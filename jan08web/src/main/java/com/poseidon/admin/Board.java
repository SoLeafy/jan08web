package com.poseidon.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.AdminDAO;
import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/admin/board")
public class Board extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Board() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		if (request.getParameter("page") != null && request.getParameter("page") != "") {
			page = Util.str2Int(request.getParameter("page"));
		}
		
		AdminDAO dao = new AdminDAO();
		List<BoardDTO> list = dao.boardList(page);
		int totalCount = dao.totalCount();
		
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("page", page);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/board.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
