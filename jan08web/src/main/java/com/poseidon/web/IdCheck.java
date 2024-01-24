package com.poseidon.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;

@WebServlet("/idCheck")
public class IdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IdCheck() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		//db에게 물어보기
		//dto에 담아도 되고...
		MemberDTO dto = new MemberDTO();
		dto.setMid(id);
		
		//dao 필요. result 1 혹은 0 날아오게.
		MemberDAO dao = new MemberDAO();
		int result = dao.checkId(dto);
		
		//System.out.println("검사 요청이 들어온 ID : " + id + " 결과는 : " + result);
		PrintWriter pw = response.getWriter();
		pw.print(result);
	}

}
