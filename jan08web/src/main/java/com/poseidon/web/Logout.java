package com.poseidon.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("get으로 들어왔어요");
		/*
		 * 세션 쿠키 (서로 반대됨)
		 * 세션 : 서버(우리는 톰캣)에 저장됨. 필요할 때마다 서버에서 불러다 써서 보안에 강력
		 * - 로그인 정보
		 * - 자바
		 * - 은닉형태. 서버가 느려질 순 있음.
		 * 
		 * 쿠키 : 클라이언트(브라우저)에 저장됨. 쿠키를 조작하면 조작된 쿠키가 서버에 올라감. 보안에 취약. 수억~군데에서 관리
		 * - 쇼핑정보, 장바구니, 방문내역, 로그인 정보 같은 것도 있다.
		 * - 스크립트(계열들이 만들어서 저장. JS같은..)
		 * - 오픈형태. 브라우저 방문하면 해당? 쿠키 볼 수 있음.
		 * 
		 * 요즘은 JWT(javascript web token) 토큰이라는 것도 쓴다.
		 */
		//세션 종료, 세션 삭제
		HttpSession session = request.getSession(); //세션이라는 객체 만들고 if문으로 불러올 것.
		if(session.getAttribute("mname") != null) { //세션이 있으면 -> 종료
			//session.setMaxInactiveInterval(3600); // 세션 시간 연장 (은행사이트에서 하는 그거)
			//System.out.println("세션 유효시간 : " + session.getMaxInactiveInterval()); //세션이 만든지 얼마나 됐는지, 남은 시간은? 기본적으로 30분(1800). web xml에서 조정가능.
			//System.out.println("mname : " + session.getAttribute("mname"));// 만들어 둔 이름을 알아야 뽑아볼 수 있다.
			session.removeAttribute("mname");
		}
		if(session.getAttribute("mid") != null) { //세션이 2개. 근데 어차피 종료하러 들어왔으니 if로 물을 것도 없이 로그아웃
			//System.out.println("mid : " + session.getAttribute("mid"));
			session.removeAttribute("mid");
		}
		session.invalidate();// 해당 브라우저와 연결된 모든 녀석들을 삭제해. (위에는 하나하나 중지)
		// invalidate()는 세션 자체를 무효화하고 제거하고
		// removeAttribute()는 현재 세션에서 특정 key-value만 제거한다.
		// removeAttribute()로 키만 제거하면 httpSession 인스턴스가 남아 있어(흔적 남음) invalidate()로 해주는 게 좋다.
		//login 페이지로 보내기
		//response.sendRedirect("./logout.jsp"); //정확히 보려고 logout 페이지로
		RequestDispatcher rd = request.getRequestDispatcher("logout.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		System.out.println("post로 들어왔어요");
	}

}
