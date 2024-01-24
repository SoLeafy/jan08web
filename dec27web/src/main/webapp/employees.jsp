<%@page import="com.poseidon.db.EmployeesDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.poseidon.db.EmployeesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원 목록</title>
</head>
<body>
	<h1>직원 목록</h1>
	<!-- 1. import DAO -->
	<%
	//자바 영역입니다.
	EmployeesDAO dao = new EmployeesDAO();
	List<EmployeesDTO> list = dao.selectEmployees();
	%>
	<!-- 2. 값 받기 -->
	
	<!-- 3. 값 출력 -->
	<table border="1">
		<tr>
			<td>emp_no</td>
			<td>birth_date</td>
			<td>first_name</td>
			<td>last_name</td>
			<td>gender</td>
			<td>hire_date</td>
		</tr>
		<%
		//에러 발생하면 중요 코드 노출...
		for(int i = 0; i < list.size(); i++) {
		%>
		<tr>
			<td><%=list.get(i).getEmp_no() %></td>
			<td><%=list.get(i).getBirth_date() %></td>
			<td><%=list.get(i).getFirst_name() %></td>
			<td><%=list.get(i).getLast_name() %></td>
			<td><%=list.get(i).getGender() %></td>
			<td><%=list.get(i).getHire_date() %></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>