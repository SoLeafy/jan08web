<%@page import="com.poseidon.dto.EmployeesDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.poseidon.dao.EmployeesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//여기는 자바 영역
EmployeesDAO dao = new EmployeesDAO();
List<EmployeesDTO> list = dao.selectEmployees();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 여기서 출력합니다. -->
	전체 글 수 : <%=list.size() %><br>
	
	<table border="1">
		<thead>
		<tr>
			<th>emp_no</th>
			<th>birth_date</th>
			<th>first_name</th>
			<th>last_name</th>
			<th>gender</th>
			<th>hire_date</th>
		</tr>
		</thead>
		<tbody>
		<% for (EmployeesDTO dto : list) { // forEach문도 된다! %>
		<tr>
			<td><%=dto.getEmp_no() %></td>
			<td><%=dto.getBirth_date() %></td>
			<td><%=dto.getFirst_name() %></td>
			<td><%=dto.getLast_name() %></td>
			<td><%=dto.getGender() %></td>
			<td><%=dto.getHire_date() %></td>
		</tr>
		<%} %>
		</tbody>
	</table>
	
	<a href="./main.jsp">이동합니다.</a>
	
</body>
</html>