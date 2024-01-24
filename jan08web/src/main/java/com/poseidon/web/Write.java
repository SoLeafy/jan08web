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

@WebServlet("/write")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Write() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		if(session.getAttribute("mname") == null) {
			response.sendRedirect("./login"); //url까지 변경해서 아예 보내 버려서 화면 보여줌.
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("write.jsp"); //url 고정, 화면만 변경
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글처리 (젤위에 있어야하나봐)
		request.setCharacterEncoding("UTF-8");
		
		//세션에 들어있는 mid 가져오기 2021-01-15 / poseidon
		HttpSession session = request.getSession();
		// if문으로 로그인 되어있는(=세션이 있는) 사람만 아래 로직 수행하도록 변경할 것.
		if(session.getAttribute("mid") == null || session.getAttribute("mname") == null) {
			//로그인하지 않았다면 login으로 가게 할 것. (혹시나 뚫었을 경우에 대비한 것. 이미 /write 쳐서 들어오는 것도 막음.)
			response.sendRedirect("./login?login=nologin");
		} else {
			//로그인 했다면 아래 로직을 수행하게 할 것.
			//form에 묶여 오는 거 2개의 name. title, content
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			//HTML에서 태그를 특수기호로 변경하기
			title = Util.removeTag(title);
			
			//DAO에 write 메소드 만들기
			//dto 담아서 write
			BoardDTO dto = new BoardDTO();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setMid((String) session.getAttribute("mid")); //세션에서 mid 가져옵니다.
			dto.setIp(Util.getIp(request));
			//session은 Object로 올리고 내려서,, 배열로 하는 회사도 있다.
			
			BoardDAO dao = new BoardDAO();
			int result = dao.write(dto); // 정상적 수행이 되면 board로, 비정상 시 error로
			System.out.println("글쓰기 결과는 : " + result);
			
			//페이지 이동하기
			//디스패쳐는 url은 안 바뀌고 내용만 바뀌니까 아예 url도 바꿔 보내버려야 함
			if(result == 1) {
				response.sendRedirect("./board");
			} else {
				response.sendRedirect("./error.jsp");
			}
		}
		
		
	}

}
