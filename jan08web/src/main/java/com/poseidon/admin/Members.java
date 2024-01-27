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
import com.poseidon.dto.MemberDTO;
import com.poseidon.util.Util;

@WebServlet("/admin/members")
public class Members extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Members() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getParameter("grade"));
		
		AdminDAO dao = new AdminDAO();
		List<MemberDTO> list = null;
		if(request.getParameter("grade") == null || request.getParameter("grade").equals("")) { // equals는 grade= <-로 뒤에 아무것도 없는 url 상황.
			list = dao.memberList();
		} else {
			list = dao.memberList(Util.str2Int(request.getParameter("grade")));
		}
		//if(request.getParameter("grade") != null) {
		//	list = dao.memberList(Util.str2Int(request.getParameter("grade")));
		//} else {
		//	list = dao.memberList();
		//}
		
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/members.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//db에 변경
		MemberDTO dto = new MemberDTO();
		dto.setMno(Util.str2Int(request.getParameter("mno")));
		dto.setMgrade(Integer.parseInt(request.getParameter("grade")));
		
		AdminDAO dao = new AdminDAO();
		int result = dao.updateMgrade(dto);
		System.out.println("등급 변경 결과는: " + result);
		
		//페이지 이동 -> 화면 아예 깜빡이게.
		if(request.getParameter("currentgrade") == null) {
			response.sendRedirect("./members");
		} else {
			response.sendRedirect("./members?grade=" + request.getParameter("currentgrade")); //admin 안 붙이는 이유 = 지금 내 위치가 admin이라서
		}
	}

}
