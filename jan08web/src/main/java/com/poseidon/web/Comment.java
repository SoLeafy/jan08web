package com.poseidon.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poseidon.dao.CommentDAO;
import com.poseidon.dto.CommentDTO;
import com.poseidon.util.Util;

@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Comment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//한글처리
		HttpSession session = request.getSession();
		
		if(request.getParameter("commentcontent") != null 
				&& Util.intCheck(request.getParameter("bno"))
				&& session.getAttribute("mid") != null) {
			
			//오는 값 받기
			String commentcontent = request.getParameter("commentcontent");// 댓글내용
			
			//HTML에서 태그를 특수기호로 변경하기
			//줄바꿈처리
			commentcontent = Util.removeTag(commentcontent);
			
			//엔터처리 /r /n /nr
			commentcontent = Util.addBr(commentcontent);
			
			String bno = request.getParameter("bno");
			System.out.println(commentcontent + " : " + bno);
			//저장해주세요
			CommentDTO dto = new CommentDTO();
			dto.setComment(commentcontent);
			dto.setBoard_no(Util.str2Int(bno));
			dto.setMid((String) session.getAttribute("mid"));
			dto.setIp(Util.getIp(request));
			
			CommentDAO dao = new CommentDAO();
			int result = dao.writeComment(dto);
			//System.out.println("댓글 처리 결과: " + result);
			
			//이동해주세요
			if (result == 1) {
				response.sendRedirect("./detail?no="+bno);
			} else {
				response.sendRedirect("./error.jsp");
			}
		} else {
			response.sendRedirect("./error.jsp");
			//로그인하지 않았다면 login으로 가게 할 것. (혹시나 뚫었을 경우에 대비한 것. 이미 /write 쳐서 들어오는 것도 막음.)
			//response.sendRedirect("./login?login=nologin");
		}
	}

}
