package com.poseidon.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//요기가 중요.
@WebServlet("/admin/index") //url의 경로 = 실제 파일과 다르게 호출할 url을 지정함.
public class AdminIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndex() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath()); //뭐 어떻게 요청 들어왔다? 정보 보여주는 거.
		RequestDispatcher rd = request.getRequestDispatcher("/admin/admin.jsp");//파일 있는 경로
		rd.forward(request, response);
		// url을 /admin/admin으로 하위에 들어가게 쓰고 싶다. 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response); //Post 왔을 때 doGet한테 일을 시킬거야, 하는 의미.
	}

}
