package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.BoardDAO;

@WebServlet("/write")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Write() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//페이지 열기만
		System.out.println("서블릿을 통과했습니다.");
		//response.sendRedirect("write.jsp");
		// forward라는 것도 있다.
		RequestDispatcher rd = request.getRequestDispatcher("write.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8"); //한글 처리(깨지지 않게)
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardDAO dao = new BoardDAO();
		int result = dao.write(title, content);
		
		if(result == 1) { // result가 1이 아니면 error로 간다고 해서 이미지가 저장안됨.
			response.sendRedirect("./index.jsp");
		} else {
			response.sendRedirect("./error.jsp");
		}
		
		// 지금은 해당하지 않는다. (나갈 데이터가 있을 때)
		// 나가는 type도 UTF-8
		//response.setContentType("text/html;charset=UTF-8");
		
	}

}
