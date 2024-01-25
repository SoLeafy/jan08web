package com.poseidon.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.CafeDAO;
import com.poseidon.dto.CafeDTO;

@WebServlet("/cafe")
public class Cafe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cafe() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CafeDAO로 현황...
		CafeDAO dao = new CafeDAO();
		int iceAmeCount = dao.countMenu("ice-americano");
		int hotAmeCount = dao.countMenu("hot-americano");
		int iceTeaCount = dao.countMenu("ice-tea");
		int hotTeaCount = dao.countMenu("hot-tea");
		int nothingCount = dao.countMenu("no-drink");
		List<Integer> notYet = dao.notYet();
		//누가 뭐 시켰는지
		List<Integer> iceAmeList = dao.whoseMenu("ice-americano");
		List<Integer> hotAmeList = dao.whoseMenu("hot-americano");
		List<Integer> iceTeaList = dao.whoseMenu("ice-tea");
		List<Integer> hotTeaList = dao.whoseMenu("hot-tea");
		List<Integer> nothingList = dao.whoseMenu("no-drink");
		
		
		request.setAttribute("iceAmeTotal", iceAmeCount);
		request.setAttribute("hotAmeTotal", hotAmeCount);
		request.setAttribute("iceTeaTotal", iceTeaCount);
		request.setAttribute("hotTeaTotal", hotTeaCount);
		request.setAttribute("nothingTotal", nothingCount);
		request.setAttribute("notYetList", notYet);
		
		request.setAttribute("iceAmeList", iceAmeList);
		request.setAttribute("hotAmeList", hotAmeList);
		request.setAttribute("iceTeaList", iceTeaList);
		request.setAttribute("hotTeaList", hotTeaList);
		request.setAttribute("nothingList", nothingList);
		
		RequestDispatcher rd = request.getRequestDispatcher("cafe.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String studentNo = request.getParameter("studentNo");
		String menu = request.getParameter("menu");
		String comment = request.getParameter("menu-comment");
		
		CafeDTO dto = new CafeDTO();
		dto.setStudentNo(Integer.parseInt(studentNo));
		dto.setMenu(menu);
		dto.setMenuComment(comment);
		
		CafeDAO dao = new CafeDAO();
		int result = dao.saveMenu(dto);
		
		System.out.println("투표 입력 결과: " + result);
		
		RequestDispatcher rd = request.getRequestDispatcher("cafe.jsp");
		rd.forward(request, response);
	}

}
