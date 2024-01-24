package com.poseidon.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poseidon.dao.LogDAO;
import com.poseidon.util.Util;

@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Index() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get식 오픈 url은 똑같이 다른 사람한테 주면 그대로 같은 거 볼 수 있음. (홍보용,, 기사페이지나 sns등)
		
		//response.sendRedirect("index.jsp"); //jsp 막아서 리다이렉트 안됨. 403 금지됨.
		/*
		 * List<Map<String, String>> map = new ArrayList<Map<String, String>>();
		 * 
		 * //요소 만들기 (똑같은 Map으로 만들지 않으면 안들어감) Map<String, String> e = new HashMap<String,
		 * String>(); e.put("name", "김땡땡"); e.put("age", "120"); e.put("addr", "한양");
		 * 
		 * map.add(e);//0번지에 들어갔다.
		 * 
		 * e = new HashMap<String, String>();// <- 이렇게 한 이유. 다른 인스턴스를 만들었음.
		 * e.put("name", "이땡땡"); e.put("age", "12"); e.put("addr", "서울");
		 * map.add(e);//1번지... new 안하면 주소가 같아서 중복돼서 안 들어간다. (덮어씌워지니까.) //자바는 이름이 소멸돼서 절대
		 * 찾을 수 없다. GC가 처리함. C언어 계열은 찾을 수 있음. 메모리에 직접 가니까.
		 * 
		 * //jsp?로 보내준다. request.setAttribute("map", map); //꺼내올 때 이름, 보낼 값. key,
		 * value라고 생각.
		 */
		//로그인한 사용자가 이렇게이렇게 돌아다녔어, 추적??
		//log
		LogDAO log = new LogDAO();
		log.logWrite(Util.getIp(request), "./index", null);
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post는 혼자 봐야하는 거. 내 개인정보, 메일 등등
	}

}
