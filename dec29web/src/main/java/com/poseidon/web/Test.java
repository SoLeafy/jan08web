package com.poseidon.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//servlet은 jsp랑 다르게 java니까 컴파일하고 써야 해서 서버 재기동
@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L; //이 줄 뭔지 공부해오기
       
    public Test() {
        super();
    }

    //이 형태가 Spring으로 넘어갈 것. Spring의 원시적인 모양. 90년대
    //Get
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String str = "pupu";
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title>타이틀입니다.</title>");
		pw.println("<style type=\"text/css\">");
		pw.println("</style>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h1>서블릿이 호출되었습니다.</h1>");
		pw.println("<img src=\"\">");
		pw.println("당신의 이름은: " + str + "<br>");
		pw.println("이렇게 작업해야 해요. 너무 힘들어요.<br>");
		pw.println("수정하려면 다시 컴파일해서 실행해야 해요.<br>");
		pw.println("이것보다 jsp가 조금 더 편합니다. 아니 더 편해요.<br>");
		pw.println("<a href=\"./main.jsp\">main</a>");
		pw.println("</body>");
		pw.println("</html>");
	}

	//Post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

/*
 * request	: 클라이언트가 서버에 요청하는 것.
 * response	: 서버가 클라이언트의 요청에 응답하는 것.
 * 
 * get방식	: 오픈, url 뒤에 요청 정보를 붙여 보내는 방식
 * https://www.clien.net/service/board/news/18500330?od=T31&po=0&category=0&groupCd=
 * 
 * post방식	: 숨김. url은 그대로. http바디에 요청 정보를 숨겨서 보냄. (로그인 페이지 아이디 패스워드 url에 안나오게)
 * https://www.clien.net/service/board/news/18500330
 * 
 */
