package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.BoardDAO;
import com.poseidon.dto.BoardDTO;
import com.poseidon.util.Util;

@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Update() {
		super();
	}

	// 오픈 방식으로 호출했으니까 GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 세션이 있을 때 = 정상작업하기
		if (session.getAttribute("mid") != null) {
			// no잡기
			int no = Util.str2Int(request.getParameter("no"));
			// DAO에게 질의하기
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = dao.detail(no); // update 대신 detail 재활용
			//System.out.println(dto.getMid().equals(session.getAttribute("mid"))); // true
			//System.out.println(session.getAttribute("mid").equals(dto.getMid())); // true
			//System.out.println(session.getAttribute("mid") == dto.getMid()); // false
			//System.out.println(((String)session.getAttribute("mid")).equals(dto.getMid())); // true
			
			if(dto.getMid().equals(session.getAttribute("mid"))) {
				// jsp에 보내기
				request.setAttribute("update", dto); // update라는 이름으로 보내기
				RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect("./error.jsp");
			}

		} else {
			response.sendRedirect("./login?login=nologin");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// doGet(request, response);

		HttpSession session = request.getSession();

		if (request.getParameter("title") != null && request.getParameter("content") != null
				&& Util.intCheck(request.getParameter("no")) && session.getAttribute("mid") != null) {
			// 진짜 수정
			BoardDTO dto = new BoardDTO();
			dto.setTitle(request.getParameter("title"));
			dto.setContent(request.getParameter("content"));
			dto.setNo(Util.str2Int(request.getParameter("no")));
			dto.setMid((String) session.getAttribute("mid"));

			BoardDAO dao = new BoardDAO();
			int result = dao.update(dto);
			System.out.println("수정 결과 : " + result);

			response.sendRedirect("./detail?no=" + request.getParameter("no"));
		} else {
			// error
			response.sendRedirect("./error.jsp");
		}

//		//세 값이 다 와야 다음을 진행할 수 있따.
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//		String no = request.getParameter("no");
//		
//		System.out.println(title);
//		System.out.println(content);
//		System.out.println(no);

	}

}
