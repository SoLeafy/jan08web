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

@WebServlet("/recomment")
public class Recomment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Recomment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//post에 들어온 거 잡아
		System.out.println(request.getParameter("cno"));
		System.out.println(request.getParameter("comment"));
		
		HttpSession session = request.getSession();
		int result = 0;
		if(request.getParameter("comment") != null && Util.intCheck(request.getParameter("cno"))
				&& session.getAttribute("mid") != null) {
			
			CommentDTO dto = new CommentDTO();
			dto.setMid((String) session.getAttribute("mid"));
			dto.setCno(Util.str2Int2(request.getParameter("cno")));
			dto.setComment(Util.addBr(request.getParameter("comment")));
			//dto.setComment(request.getParameter("comment")); //db에 <br> 저장 안하기..?
			
			CommentDAO dao = new CommentDAO();
			result = dao.commentUpdate(dto);
		}
		PrintWriter pw = response.getWriter();
		pw.print(result);
	}

}
