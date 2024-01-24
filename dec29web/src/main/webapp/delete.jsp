<%@page import="com.poseidon.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제</title>
</head>
<body>
	<!-- 참고로 이 페이지는 삭제 기능만 수행하고 페이지 이동합니다. -->
	<%
	String no = request.getParameter("no");
	
	BoardDAO dao = new BoardDAO();
	dao.delete(no);
	
	// 페이지 이동
	response.sendRedirect("./index.jsp");
	
	%>
	
	<!-- 바로 페이지 이동해버려서 이 아래는 못 봄. 자바코드만 보니까 jsp 아니고 servlet으로 함 -->
	글자
	글자
</body>
</html>