package com.poseidon.web;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;

@WebServlet("/myNickname")
public class MyNickname extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyNickname() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		if(session.getAttribute("mid") != null) {
			//mid를 데이터베이스에 질의
			MemberDTO dto = new MemberDTO();
			dto.setMid((String) session.getAttribute("mid"));
			
			MemberDAO dao = new MemberDAO();
			//DTO에 담아서
			dto = dao.myInfo(dto);
			
			//myInfo.jsp에 찍어주도록 해주세요.
			request.setAttribute("myInfo", dto);//dto도 담아서 보내야해서.
			RequestDispatcher rd = request.getRequestDispatcher("myinfo.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("./login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//doGet(request, response);
		HttpSession session = request.getSession();
		if(session.getAttribute("mid") != null) {
			MemberDTO dto = new MemberDTO();
			dto.setMname(request.getParameter("name"));
			dto.setMid((String) session.getAttribute("mid"));
			MemberDAO dao = new MemberDAO();
			dao.newName(dto);
			session.setAttribute("mname", dto.getMname()); //세션에서 이름 변경. (메뉴에서 바로 바뀌도록)
			response.sendRedirect("./myInfo");
		} else {
			response.sendRedirect("./login");
		}
	}

}
