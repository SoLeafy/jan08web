package com.poseidon.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.CommentDAO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

@WebServlet("/commentDel")
public class CommentDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentDel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("mid") != null && Util.intCheck(request.getParameter("no")) && Util.intCheck2(request.getParameter("cno"))) {
			//System.out.println("no: " + request.getParameter("no"));
			//System.out.println("cno: " + request.getParameter("cno"));
			CommentDTO dto = new CommentDTO();
			dto.setMid((String) session.getAttribute("mid"));
			dto.setCno(Util.str2Int(request.getParameter("cno")));
			//dto.setBoard_no(Util.str2Int(request.getParameter("no")));
			
			CommentDAO dao = new CommentDAO();
			int result = dao.commentDelete(dto);
			if(result == 1) {
				response.sendRedirect("./detail?no="+request.getParameter("no"));
			} else {
				response.sendRedirect("./error.jsp");
			}
			
		} else {
			response.sendRedirect("./error.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("요청이 들어왔습니다.");
		//System.out.println(request.getParameter("no"));
		
		//Get꺼를 여기다 복붙해서 갖다썼다.
		HttpSession session = request.getSession();
		int result = 0;
		if(session.getAttribute("mid") != null && Util.intCheck2(request.getParameter("no"))) { //cno를 no라고 했기 때문에...
			
			CommentDTO dto = new CommentDTO();
			dto.setMid((String) session.getAttribute("mid"));
			dto.setCno(Util.str2Int(request.getParameter("no")));
			
			CommentDAO dao = new CommentDAO();
			result = dao.commentDelete(dto);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		//1인 경우(삭제했으면) 1로, 1 아니면 다 0
		//PrintWriter : 한꺼번에 보낼 때 제일 큰 녀석.
		PrintWriter pw = response.getWriter(); //이렇게 보내야 jsp에 적힌 ajax에서 받아서 쓴다. (Team.java, join.jsp에도 써져 있다.)
		pw.print(result); //원시 형태로 날리고 있다...(나중에 Spring에서 뭐 더)
	}

}
