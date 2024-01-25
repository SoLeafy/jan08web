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
		
		//한 사람 안 한 사람 따로
		List<Integer> studentsDone = dao.studentsDone();
		
		List<Integer> students = new ArrayList<Integer>();
		for(int i = 1; i < 28; i++) {
			students.add(i);
		}
		//students에 있는 사람 중... list에 없는 사람. .contains
		for(int i = 1; i < studentsDone.size(); i++) {
			if(students.contains(studentsDone.get(i))) {
				students.remove(studentsDone.get(i));
			}
		}
		
		//누가 뭐 시켰는지
		List<Integer> iceAmeList = dao.whoseMenu("ice-americano");
		List<Integer> hotAmeList = dao.whoseMenu("hot-americano");
		List<Map<Integer, String>> iceTeaList = dao.teaMenu("ice-tea");
		List<Map<Integer, String>> hotTeaList = dao.teaMenu("hot-tea");
		List<Integer> nothingList = dao.whoseMenu("no-drink");
		
		//jsp로 보내기
		//총인원
		request.setAttribute("iceAmeTotal", iceAmeCount);
		request.setAttribute("hotAmeTotal", hotAmeCount);
		request.setAttribute("iceTeaTotal", iceTeaCount);
		request.setAttribute("hotTeaTotal", hotTeaCount);
		request.setAttribute("nothingTotal", nothingCount);
		//안한사람과 한사람
		request.setAttribute("notYetList", students);
		request.setAttribute("studentsDone", studentsDone);
		
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
		
		// 이렇게 하면 jsp만 보여주니까 다시 GET으로 가게 sendRedirect를 써준다!!! (문제해결)
		//RequestDispatcher rd = request.getRequestDispatcher("cafe.jsp");
		//rd.forward(request, response);
		response.sendRedirect("./cafe");
	}

}
