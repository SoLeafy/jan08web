<%@page import="com.poseidon.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이거 서블릿으로 변경하겠습니다.</title>
</head>
<body>
	<!-- 데이터베이스에 값 전달하고 끝. -->
	<%
	request.setCharacterEncoding("UTF-8"); //한글 처리(깨지지 않게)
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	//할일.
	//DAO 호출
	BoardDAO dao = new BoardDAO();
	//int write(title, content); 메소드
	int result = dao.write(title, content);
	
	//response 이용한 페이지 이동 첫 번째 방법
	if(result == 1) {
		response.sendRedirect("./index.jsp");
	} else {
		response.sendRedirect("./error.jsp");
	}
	//db 저장됐다면 페이지 이동
	// 1이면 올바른 페이지로 이동
	// 0이면 오류 페이지로 이동
	/*
	<!-- 액션 태그-->
	<!-- 줄에 닿자마자 페이지 이동 -->
	<jsp:forward page="./main.jsp"/> <!-- 한 줄로 줄일 때 /> -->
	
	<!-- 자바스크립트 -->
	<script type="text/javascript">
		location.href="./main.jsp";
	</script>
	*/
	%>
	
	
	
	
	
	
</body>
</html>