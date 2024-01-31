package com.poseidon.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.AdminDAO;

@WebServlet("/admin/ip")
public class Ip extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ip() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("ip"));
		
		AdminDAO dao = new AdminDAO();
		List<Map<String, Object>> list = null;
		if(request.getParameter("ip") != null && !request.getParameter("ip").equals("")) {
			list = dao.ipList(request.getParameter("ip"));
		} else {
			list = dao.ipList();
		}
		
		//List<Map<String, Object>> topIpList = dao.topIpList();
		List<Map<String, Object>> latestAccessIP10 = dao.latestAccessIP10();
		
		request.setAttribute("list", list);
		//request.setAttribute("ipList", dao.ipList(request.getParameter("ip")));
		request.setAttribute("topIpList", dao.topIpList());//짧게 쓸려고 람다식 쓰는 사람도
		request.setAttribute("latestAccessIP10", latestAccessIP10);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/ip.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
