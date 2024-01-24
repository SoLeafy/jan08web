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

@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Update() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
		//DAO 부르기
		BoardDAO dao = new BoardDAO();
		//날아오는 번호(no) 잡기	./update?no=10
		String no = request.getParameter("no");
		//데이터베이스에 질의해서 레코드 1개 가져오기
		Map<String, Object> detail = dao.detail(no); //update는 detail 재활용!
		//update.jsp에게 돌려주기 = 페이지 이동
		//System.out.println(detail);
		
		//update.jsp에 값 보내기
		request.setAttribute("detail", detail);// 변수명, 값
		
		//jsp 부르기
		RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
		rd.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //한글 깨짐 방지
		String title = request.getParameter("title"); //form의 name 속성으로 한 걸 쓰면 된다.
		String no = request.getParameter("no");
		String content = request.getParameter("content");
		
		BoardDAO dao = new BoardDAO();
		dao.update(no, title, content);
		
		response.sendRedirect("./detail?no="+no);
		
	}

}
