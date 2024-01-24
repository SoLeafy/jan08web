package com.poseidon.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.BoardDAO;

@WebServlet("/detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Detail() {
        super();
    }

    //source? 없으니까 get
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터베이스에게 물어보고
		String no = request.getParameter("no"); //"3"
		
		//DAO에게 일 시키기
		BoardDAO dao = new BoardDAO();
		Map<String, Object> detail = dao.detail(no);
		
		//값 보내기
		request.setAttribute("detail", detail); //key value라고 보면 된다.
		
		//해당 내용 DAO(Map)에 담아서 페이지 이동
		RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
