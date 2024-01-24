package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.MemberDAO;
import com.poseidon.dto.MemberDTO;

@WebServlet("/join") //컨트롤러, 리포지토리... @를 잘봐두자.
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Join() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("join.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//값 잡아주기.
		String memId = request.getParameter("id");
		String memName = request.getParameter("name");
		String memPw = request.getParameter("pw1");
		//System.out.println(memId + memName + memPw);
		
		//db 보내기
		MemberDTO dto = new MemberDTO();
		dto.setMid(memId);
		dto.setMname(memName);
		dto.setMpw(memPw);
		
		MemberDAO dao = new MemberDAO();
		int result = dao.joinMember(dto);
		System.out.println("회원가입 결과는: " + result);
		
		//정상적으로 데이터입력을 완료했다면 로그인 페이지,
		//비정상이면 에러로 보내기.
		if(result == 1) {
			response.sendRedirect("./login");
		} else {
			response.sendRedirect("./error.jsp");
		}
		
	}

}
