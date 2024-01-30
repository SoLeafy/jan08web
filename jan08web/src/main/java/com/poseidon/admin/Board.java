package com.poseidon.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.AdminDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/admin/board")
public class Board extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Board() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("search : " + request.getParameter("search"));
		
		int page = 1;
		if (request.getParameter("page") != null && request.getParameter("page") != "") {
			page = Util.str2Int(request.getParameter("page"));
		}
		
		AdminDAO dao = new AdminDAO();
		List<BoardDTO> list  = null;
		
		//search로 들어온 거 있는지 없는지 if문
		//null이 아니다로 하면 잘 안되고 null이다로 하면 잘된다..?
		if(request.getParameter("search") == null || request.getParameter("search").equals("")) {
			list = dao.boardList(page); // search도 한 메서드에 하는 건 mybatis때
		} else {
			list = dao.boardList(request.getParameter("search"));
		}
		
		int totalCount = dao.totalCount();
		
		request.setAttribute("list", list);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("page", page);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/board.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		
		//System.out.println(request.getParameter("no"));
		//System.out.println(request.getParameter("del"));
		
		AdminDAO dao = new AdminDAO();
		BoardDTO dto = new BoardDTO();
		int result = 0;
		//if문 제어해야되고 로그인한 사용자만 할 수 있게 해야하고...
		//if(session.getAttribute("mid") != null && Util.intCheck2(request.getParameter("no"))) {
			dto.setNo(Util.str2Int(request.getParameter("no")));
			dto.setDel(Util.str2Int2(request.getParameter("del").equals("1") ? "0" : "1"));
			result = dao.boardDel(dto);
		//}
		
		PrintWriter pw = response.getWriter();
		pw.print(result);
	}

}
