<%@page import="java.util.List"%>
<%@page import="com.poseidon.team.Team"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀 뽑기</title>
</head>
<body>
	<!-- 5팀 팀 뽑기를 만들어주세요. -->
	<%
	Team teamClass = new Team();
	List<List<String>> team = teamClass.getTeam();
	%>

	<%
	for (List<String> list : team) {
		for (String str : list) {
			//여기는 자바 69804
	%>
		<!-- 여기는 html -->
		<%=str%>
	<%
		}
		%>
		<br>
		<%
	}
	//여기도 자바
	%>
	
	<a href="./board.jsp">board</a>
	
	
</body>
</html>